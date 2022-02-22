package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.SqlDto;
import mx.com.ga.cosmonaut.common.dto.consultas.DispersionConsulta;
import mx.com.ga.cosmonaut.common.dto.consultas.DispersionConsultaError;
import mx.com.ga.cosmonaut.common.dto.consultas.DispersionResumenConsulta;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.Sql;
import org.apache.commons.lang3.StringUtils;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class DispersionTimbradoRepository {

    private final JdbcOperations jdbcOperations;

    protected DispersionTimbradoRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT = "SELECT nomina.nombre_nomina AS concepto_pago, " +
                                        "(SELECT clabe FROM nma_cuenta_banco WHERE persona_id = persona.persona_id) AS cuenta_beneficiario, " +
                                        "cuentaEmpresa.num_cuenta AS cuenta_ordenante, " +
                                        "persona.nombre || ' ' || persona.apellido_pat  AS nombre_beneficiario, " +
                                        "cliente.nombre AS nombre_ordenante, " +
                                        "persona.rfc AS rfc_curp_beneficiario, " +
                                        "cliente.rfc AS rfc_curp_ordenante, " +
                                        "(SELECT bancoEmpleado.cod_banco FROM nma_cuenta_banco cuentaEmpleado INNER JOIN cs_banco bancoEmpleado ON cuentaEmpleado.banco_id = bancoEmpleado.banco_id WHERE persona_id = persona.persona_id) AS institucion_contraparte, " +
                                        "banco.cod_banco AS institucion_operante, " +
                                        "null AS referencia_numerica, " +
                                        "40 AS tipo_cuenta_beneficiario, " +
                                        "40 AS tipo_cuenta_ordenante, " +
                                        "1 AS tipo_pago, " +
                                        "empleadoNomina.total_neto AS monto ";

    private static final String SELECT_ERRORES_PAGO = "SELECT nomina.nombre_nomina AS nomina, " +
            "colaborador.num_empleado AS numero_empleado, " +
            "persona.nombre || ' ' || persona.apellido_pat AS nombre, " +
            "persona.rfc AS rfc, " +
            "banco.razon_social AS banco, " +
            "cuenta.clabe AS clave, " +
            "bitacora.es_correcto AS estatus, " +
            "bitacora.observacion AS observaciones, " +
            "bitacora.fecha_dispersion AS fecha_dispersion ";

    private static final String FROM = "FROM ncr_nomina_xperiodo nomina  " +
            "INNER JOIN ncl_centroc_cliente cliente ON nomina.centroc_cliente_id = cliente.centroc_cliente_id  " +
            "INNER JOIN ncr_empleado_xnomina empleadoNomina ON nomina.nomina_xperiodo_id = empleadoNomina.nomina_xperiodo_id  " +
            "INNER JOIN nco_contrato_colaborador colaborador ON empleadoNomina.fecha_contrato_nogrupo = colaborador.fecha_contrato  " +
            "AND colaborador.persona_id = empleadoNomina.persona_id  " +
            "AND colaborador.centroc_cliente_id = empleadoNomina.centroc_cliente_id  " +
            "INNER JOIN nma_cuenta_banco cuentaEmpresa ON nomina.cuenta_banco_id = cuentaEmpresa.cuenta_banco_id  " +
            "INNER JOIN cs_banco banco ON cuentaEmpresa.banco_id = banco.banco_id  " +
            "INNER JOIN nco_persona persona ON empleadoNomina.persona_id = persona.persona_id  " +
            "AND colaborador.metodo_pago_id = ? " +
            "AND nomina.nomina_xperiodo_id = ? " +
            "AND colaborador.fecha_contrato = ? " +
            "AND colaborador.persona_id = ? " +
            "AND colaborador.centroc_cliente_id = ? ";

    private static final String FROM_ERRORES_PAGO = "FROM ncr_bitacora_pago bitacora " +
            "INNER JOIN ncr_empleado_xnomina empleado ON bitacora.persona_id = empleado.persona_id " +
            "    AND bitacora.cliente_id = empleado.centroc_cliente_id " +
            "    AND bitacora.fecha_contrato = empleado.fecha_contrato_nogrupo " +
            "    AND bitacora.nomina_xperiodo_id = empleado.nomina_xperiodo_id " +
            "INNER JOIN nco_contrato_colaborador colaborador ON empleado.fecha_contrato_nogrupo = colaborador.fecha_contrato " +
            "    AND empleado.persona_id = colaborador.persona_id " +
            "    AND empleado.centroc_cliente_id = colaborador.centroc_cliente_id " +
            "INNER JOIN ncr_nomina_xperiodo nomina ON bitacora.nomina_xperiodo_id = nomina.nomina_xperiodo_id " +
            "INNER JOIN nco_persona persona ON bitacora.persona_id = persona.persona_id " +
            "INNER JOIN nma_cuenta_banco cuenta on persona.persona_id = cuenta.persona_id " +
            "INNER JOIN cs_banco banco on cuenta.banco_id = banco.banco_id " +
            "WHERE bitacora.nomina_xperiodo_id = ? " +
            "AND bitacora.es_actual = true " +
            "AND bitacora.es_correcto = false ";

    private static final String SELECT_RESUMEN = "SELECT COALESCE(SUM(empleado.total_neto),0) AS total_neto,\n" +
            "       COALESCE(COUNT(bitacora.bitacora_pago_id),0) AS total_pagos_realizados,\n" +
            "       COALESCE(COUNT(bitacora.bitacora_pago_id),0) AS total_recibos_pago ";

    private static final String FROM_RESUMEN = "FROM ncr_bitacora_pago bitacora\n" +
            "INNER JOIN ncr_empleado_xnomina empleado ON bitacora.persona_id = empleado.persona_id\n" +
            "AND bitacora.cliente_id = empleado.centroc_cliente_id\n" +
            "AND bitacora.fecha_contrato = empleado.fecha_contrato_nogrupo\n" +
            "AND bitacora.nomina_xperiodo_id = empleado.nomina_xperiodo_id\n" +
            "WHERE bitacora.nomina_xperiodo_id = ?\n" +
            "AND bitacora.es_correcto = true";

    @Transactional
    public DispersionConsulta consultaDispersionEmpleados(Integer nominaPeriodoId, Integer metodoPagoId, Date fechaContrato,
                                                                Integer personaId, Integer centrocClienteId) throws ServiceException {
        try {
            SqlDto sqlDto = new SqlDto();
            sqlDto.setListaDatos(new ArrayList<>());
            StringBuilder query = new StringBuilder(200);
            query.append(SELECT).append(FROM);
            sqlDto.setQuery(query.toString());
            sqlDto.getListaDatos().add(metodoPagoId);
            sqlDto.getListaDatos().add(nominaPeriodoId);
            sqlDto.getListaDatos().add(fechaContrato);
            sqlDto.getListaDatos().add(personaId);
            sqlDto.getListaDatos().add(centrocClienteId);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return Objects.requireNonNull(jdbcOperations.entityStream(resultado, DispersionConsulta.class).findAny().orElse(null));
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +"consultaDispersionEmpleados " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<DispersionConsultaError> consultaDispersionEmpleadosErrores(Integer nominaPeriodoId,List<Integer> listaPersonas) throws ServiceException {
        try {
            String arreglo = StringUtils.join(listaPersonas.iterator(),",");
            SqlDto sqlDto = new SqlDto();
            sqlDto.setListaDatos(new ArrayList<>());
            StringBuilder query = new StringBuilder(200);
            query.append(SELECT_ERRORES_PAGO).append(FROM_ERRORES_PAGO).append(" AND bitacora.persona_id IN (" + arreglo + ") ");
            sqlDto.setQuery(query.toString());
            sqlDto.getListaDatos().add(nominaPeriodoId);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, DispersionConsultaError.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +"consultaDispersionEmpleadosErrores " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<DispersionResumenConsulta> consultaResumen(Integer nominaPerdiodoId) throws ServiceException {
        try {
            return jdbcOperations.prepareStatement(SELECT_RESUMEN + FROM_RESUMEN, statement -> {
                statement.setInt(1, nominaPerdiodoId);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, DispersionResumenConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaResumen " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
