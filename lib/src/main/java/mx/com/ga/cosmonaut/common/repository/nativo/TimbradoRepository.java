package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.consultas.*;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;
import org.apache.commons.lang3.StringUtils;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class TimbradoRepository {

    private final JdbcOperations jdbcOperations;

    protected TimbradoRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT_PERCEPCIONES = "SELECT tipo.tipo_percepcion_id AS tipo_percepcion,\n" +
            "    tipo.descripcion AS concepto,\n" +
            "    tipo.c_tipo_otro_pago AS otro_pago,\n" +
            "    percepcion.monto_exento AS importe_exento,\n" +
            "    percepcion.monto_gravable AS importe_gravable,\n" +
            "    percepcion.monto_total AS monto_total,\n" +
            "    tipo.tipo_percepcion_id AS clave ";

    private static final String SELECT_DEDUCCIONES = "SELECT tipo.tipo_deduccion_id AS tipo_deduccion,\n" +
            "    tipo.descripcion AS concepto,\n" +
            "    tipo.especializacion AS clave,\n" +
            "    deduccion.monto_cuota AS importe\n ";

    private static final String FROM_PERCEPCIONES = "FROM ncr_percepcion_xnomina percepcion\n" +
            "         INNER JOIN nmm_configura_percepcion cofiguracionPercepcion ON percepcion.configura_percepcion_id = cofiguracionPercepcion.configura_percepcion_id\n" +
            "         INNER JOIN nmm_concepto_percepcion conceptoPercepcion ON conceptoPercepcion.concepto_percepcion_id = cofiguracionPercepcion.concepto_percepcion_id\n" +
            "                                                                        AND conceptoPercepcion.tipo_percepcion_id = cofiguracionPercepcion.tipo_percepcion_id\n" +
            "                                                                        AND conceptoPercepcion.especializacion = cofiguracionPercepcion.especializacion\n" +
            "         INNER JOIN cs_tipo_percepcion tipo ON conceptoPercepcion.tipo_percepcion_id = tipo.tipo_percepcion_id\n" +
            "                                                                        AND conceptoPercepcion.especializacion = tipo.especializacion\n" +
            "WHERE percepcion.nomina_xperiodo_id =  ?\n" +
            "  AND percepcion.persona_id =  ?\n" +
            "UNION\n" +
            "SELECT tipo.tipo_percepcion_id AS tipo_percepcion,\n" +
            "       tipo.descripcion AS concepto,\n" +
            "       tipo.c_tipo_otro_pago AS otro_pago,\n" +
            "       percepcion.monto_exento AS monto_exento,\n" +
            "       percepcion.monto_gravable AS monto_gravable,\n" +
            "       percepcion.monto_total AS monto_total,\n" +
            "       tipo.tipo_percepcion_id AS especializacion\n" +
            "FROM ncr_percepcion_scfg_xnomina percepcion\n" +
            "         INNER JOIN nmm_concepto_percepcion conceptoPercepcion ON percepcion.tipo_percepcion_id = conceptoPercepcion.tipo_percepcion_id\n" +
            "                                                                      AND percepcion.concepto_percepcion_id = conceptoPercepcion.concepto_percepcion_id\n" +
            "                                                                      AND percepcion.especializacion = conceptoPercepcion.especializacion\n" +
            "         INNER JOIN cs_tipo_percepcion tipo ON conceptoPercepcion.tipo_percepcion_id = tipo.tipo_percepcion_id\n" +
            "                                                                      AND conceptoPercepcion.especializacion = tipo.especializacion\n" +
            "WHERE percepcion.nomina_xperiodo_id =  ?\n" +
            "  AND percepcion.persona_id = ? ";

    private static final String FROM_DEDUCCIONES = "FROM ncr_deduccion_xnomina deduccion\n" +
            "         INNER JOIN nmm_configura_deduccion configuracion ON deduccion.configura_deduccion_id = configuracion.configura_deduccion_id\n" +
            "         INNER JOIN nmm_concepto_deduccion concepto ON configuracion.concepto_deduccion_id = concepto.concepto_deduccion_id\n" +
            "                                                       AND configuracion.tipo_deduccion_id = concepto.tipo_deduccion_id\n" +
            "         INNER JOIN cs_tipo_deduccion tipo ON configuracion.tipo_deduccion_id = tipo.tipo_deduccion_id\n" +
            "WHERE deduccion.nomina_xperiodo_id = ?\n" +
            "  AND deduccion.persona_id = ?\n" +
            "UNION\n" +
            "SELECT tipo.tipo_deduccion_id AS tipo_deduccion,\n" +
            "       tipo.descripcion AS concepto,\n" +
            "       tipo.especializacion AS clave,\n" +
            "       deduccion.monto_cuota AS importe\n" +
            "FROM ncr_deduccion_scfg_xnomina deduccion\n" +
            "         INNER JOIN nmm_concepto_deduccion concepto ON deduccion.concepto_deduccion_id = concepto.concepto_deduccion_id\n" +
            "                                                         AND deduccion.tipo_deduccion_id = concepto.tipo_deduccion_id\n" +
            "         INNER JOIN cs_tipo_deduccion tipo ON concepto.tipo_deduccion_id = tipo.tipo_deduccion_id\n" +
            "WHERE deduccion.nomina_xperiodo_id = ?\n" +
            "  AND deduccion.persona_id = ? ";

    private static final String FROM = "SELECT empleado.nomina_xperiodo_id,\n" +
            "       empleado.fecha_contrato_nogrupo,\n" +
            "       empleado.centroc_cliente_id,\n" +
            "       empleado.persona_id,\n" +
            "       colaborador.sbc,\n" +
            "       colaborador.centroc_cliente_id,\n" +
            "       colaborador.politica_id,\n" +
            "       colaborador.grupo_nomina_id,\n" +
            "       colaborador.tipo_compensacion_id,\n" +
            "       colaborador.de_fecha_antiguedad,\n" +
            "       colaborador.fecha_contrato,\n" +
            "       empleado.total_percepciones AS sub_total,--De donde se obtiene\n" +
            "       empleado.total_deducciones AS descuento,--De donde se obtiene\n" +
            "       'MXN' AS moneda,\n" +
            "       CAST (empleado.total_percepciones - empleado.total_deducciones AS DOUBLE PRECISION) AS total,\n" +
            "       'N' AS tipo_de_comprobante,\n" +
            "       'PUE' AS metodo_pago,\n" +
            "       domicilio.d_codigo AS lugar_expedicion,\n" +
            "       empresa.rfc AS emisor_rfc,\n" +
            "       empresa.razon_social AS emisor_nombre,\n" +
            "       empresa.em_c_regimenfiscal AS emisor_regimen_fiscal,\n" +
            "       empresa.curp AS emisor_curp,\n" +
            "       persona.rfc AS receptor_rfc,\n" +
            "       persona.nombre || ' ' || persona.apellido_pat AS receptor_nombre,\n" +
            "       'P01' AS receptor_uso_cFDI,\n" +
            "       84111505 AS clave_prod_serv,\n" +
            "       1 AS cantidad,\n" +
            "       'ACT' AS clave_unidad,\n" +
            "       nomina.nombre_nomina AS descripcion,\n" +
            "       empleado.total_percepciones AS valor_unitario,\n" +
            "       empleado.total_percepciones AS importe,\n" +
            "       nomina.fecha_fin AS fecha_final_pago,\n" +
            "       nomina.fecha_inicio AS fecha_inicial_pago,\n" +
            "       now() AS fecha_pago,\n" +
            "       CAST (empleado.dias_laborados AS INTEGER) AS num_dias_pagados,\n" +
            "       SUBSTRING ( nomina.clave_periodo ,1,1) AS tipo_nomina,\n" +
            "       empleado.total_deducciones AS total_deducciones,\n" +
            "       0.00 AS total_otros_pagos,--De donde se obtiene\n" +
            "       empleado.total_percepciones AS total_percepciones,\n" +
            "       1.2 AS version,\n" +
            "       registro.registro_patronal AS registro_patronal,\n" +
            "       colaborador.de_fecha_antiguedad AS antiguedad,\n" +
            "       'DIF' AS clave_ent_fed,--Agregar campo a la tabla de estado c_Estado\n" +
            "       cuentaEmpleado.clabe AS cuenta_bancaria,\n" +
            "       persona.curp AS curp,\n" +
            "       area.descripcion AS departamento,\n" +
            "       colaborador.fecha_contrato AS fecha_inicio_rel_laboral,\n" +
            "       colaborador.num_empleado AS num_empleado,\n" +
            "       persona.nss AS num_seguridad_social,\n" +
            "       periodicidad.periodicidad_pago_id AS periodicidad_pago,\n" +
            "       puesto.descripcion AS puesto,\n" +
            "       1 AS riesgo_puesto,\n" +
            "       CAST (colaborador.sbc AS DOUBLE PRECISION) AS salario_base_cot_apor,\n" +
            "       CAST (empleado.total_sdi AS DOUBLE PRECISION) AS salario_diario_integrado,\n" +
            "       colaborador.de_es_sindicalizado AS sindicalizado,\n" +
            "       '0' || contrato.tipo_contrato_id AS tipo_contrato,\n" +
            "       jornada.tipo_jornada_id AS tipo_jornada,\n" +
            "       '0' || regimen.tipo_regimen_contratacion_id AS tipo_regimen,\n" +
            "       empleado.total_exento AS total_exento,\n" +
            "       empleado.total_gravable AS total_gravado,\n" +
            "       empleado.total_percepciones AS total_sueldos,\n" +
            "       colaborador.metodo_pago_id AS metodo_pago_id,\n" +
            "       zona_horaria.zona AS zona,\n" +
            "       empleado.dias_incapacidad AS dias_incapacidad,\n" +
            "       '0' || incapacidad.tipo_incapacidad_id as tipo_incapacidad_id\n" +
            "FROM ncr_empleado_xnomina empleado\n" +
            "INNER JOIN nco_contrato_colaborador colaborador ON empleado.fecha_contrato_nogrupo = colaborador.fecha_contrato\n" +
            "                                                       AND empleado.persona_id = colaborador.persona_id\n" +
            "                                                       AND empleado.centroc_cliente_id = colaborador.centroc_cliente_id\n" +
            "INNER JOIN ncl_area area ON colaborador.area_id = area.area_id\n" +
            "INNER JOIN ncl_grupo_nomina grupo ON colaborador.grupo_nomina_id = grupo.grupo_nomina_id\n" +
            "INNER JOIN cs_periodicidad_pago periodicidad ON grupo.periodicidad_pago_id = periodicidad.periodicidad_pago_id\n" +
            "INNER JOIN ncr_nomina_xperiodo nomina ON empleado.nomina_xperiodo_id = nomina.nomina_xperiodo_id\n" +
            "INNER JOIN nco_persona persona ON empleado.persona_id = persona.persona_id\n" +
            "LEFT JOIN nma_cuenta_banco cuentaEmpleado ON persona.persona_id = cuentaEmpleado.persona_id\n"+
            "LEFT JOIN cs_banco banco ON cuentaEmpleado.banco_id = banco.banco_id\n"+
            "INNER JOIN ncl_centroc_cliente empresa ON colaborador.centroc_cliente_id = empresa.centroc_cliente_id\n" +
            "INNER JOIN ncl_registro_patronal registro ON empresa.centroc_cliente_id = registro.centroc_cliente_id\n" +
            "INNER JOIN nma_domicilio domicilio ON empresa.centroc_cliente_id = domicilio.centroc_cliente_id\n" +
            "INNER JOIN cat_asentamiento asentamiento ON domicilio.c_estado = asentamiento.c_estado\n" +
            "                                                         AND domicilio.d_codigo = asentamiento.d_codigo\n" +
            "                                                         AND domicilio.c_mnpio = asentamiento.c_mnpio\n" +
            "                                                         AND domicilio.id_asenta_cpcons = asentamiento.id_asenta_cpcons\n" +
            "INNER JOIN cat_municipio municipio ON asentamiento.c_mnpio = municipio.c_mnpio\n" +
            "                                                         AND asentamiento.c_estado = municipio.c_estado\n" +
            "INNER JOIN cat_estado estado ON municipio.c_estado = estado.c_estado\n" +
            "INNER JOIN cat_zona_horaria_estado zona ON estado.c_estado = zona.c_estado\n" +
            "INNER JOIN cat_zona_horaria zona_horaria ON zona.id_zona_horaria = zona_horaria.id_zona_horaria\n" +
            "INNER JOIN ncl_puesto puesto ON colaborador.puesto_id = puesto.puesto_id\n" +
            "INNER JOIN ncl_jornada jornada ON colaborador.de_jornada_id = jornada.jornada_id\n" +
            "INNER JOIN cs_tipo_regimen_contratacion regimen ON colaborador.de_tipo_regimen_contratacion_id = regimen.tipo_regimen_contratacion_id\n" +
            "INNER JOIN cs_tipo_contrato contrato ON colaborador.de_tipo_contrato_id = contrato.tipo_contrato_id\n" +
            "INNER JOIN cat_metodo_pago metodo ON colaborador.metodo_pago_id = metodo.metodo_pago_id\n" +
            "LEFT JOIN nsc_incidencia incidencia ON incidencia.fecha_contrato = colaborador.fecha_contrato AND incidencia.persona_id = colaborador.persona_id AND incidencia.cliente_id = colaborador.centroc_cliente_id \n" +
            "LEFT JOIN cat_tipo_incapacidad incapacidad ON incapacidad.tipo_incapacidad_id = incidencia.tipo_incapacidad_id\n" +
            "WHERE empleado.nomina_xperiodo_id = ?\n" +
            "AND empleado.persona_id = ?\n" +
            "LIMIT 1 ";

    private static final String SELECT_CONSULTA_ERRORES = "SELECT nomina.nombre_nomina AS nomina,\n" +
            "    colaborador.num_empleado AS numero_empleado,\n" +
            "    persona.nombre || ' ' || persona.apellido_pat AS nombre,\n" +
            "    bitacora_pago.fecha_dispersion AS fecha_pago,\n" +
            "    estado.descripcion AS estatus,\n" +
            "    bitacora.observacion AS observaciones,\n" +
            "    bitacora.fecha_timbrado AS fecha_timbrado ";

    private static final String FROM_CONSULTA_ERRORES = "FROM ncr_bitacora_timbre bitacora\n" +
            "    INNER JOIN ncr_empleado_xnomina empleado ON bitacora.persona_id = empleado.persona_id\n" +
            "    AND bitacora.cliente_id = empleado.centroc_cliente_id\n" +
            "    AND bitacora.fecha_contrato = empleado.fecha_contrato_nogrupo\n" +
            "    AND bitacora.nomina_xperiodo_id = empleado.nomina_xperiodo_id\n" +
            "    INNER JOIN ncr_nomina_xperiodo nomina ON empleado.nomina_xperiodo_id = nomina.nomina_xperiodo_id\n" +
            "    INNER JOIN nco_contrato_colaborador colaborador ON empleado.fecha_contrato_nogrupo = colaborador.fecha_contrato\n" +
            "    AND empleado.persona_id = colaborador.persona_id\n" +
            "    AND empleado.centroc_cliente_id = colaborador.centroc_cliente_id\n" +
            "    INNER JOIN nco_persona persona ON colaborador.persona_id = persona.persona_id\n" +
            "    INNER JOIN cat_estado_timbre estado ON bitacora.estado_timbre_id = estado.estado_timbre_id\n" +
            "    INNER JOIN ncr_bitacora_pago bitacora_pago ON bitacora.persona_id = bitacora_pago.persona_id\n" +
            "    AND empleado.centroc_cliente_id = bitacora_pago.cliente_id\n" +
            "    AND empleado.fecha_contrato_nogrupo = bitacora_pago.fecha_contrato\n" +
            "    AND empleado.nomina_xperiodo_id = bitacora_pago.nomina_xperiodo_id\n" +
            "    WHERE bitacora.es_actual = true\n" +
            "    AND bitacora_pago.es_actual = true\n" +
            "    AND bitacora.nomina_xperiodo_id = ? ";


    private static final String SELECT_RESUMEN = "SELECT COALESCE(SUM(empleado.total_neto),0) AS total_neto,\n" +
            "       COALESCE(COUNT(bitacora.bitacora_timbre_id),0) AS total_timbrados,\n" +
            "       COALESCE(COUNT(bitacora.bitacora_timbre_id),0) AS total_recibos_timbrados ";

    private static final String FROM_RESUMEN = "FROM ncr_bitacora_timbre bitacora\n" +
            "INNER JOIN ncr_empleado_xnomina empleado ON bitacora.persona_id = empleado.persona_id\n" +
            "AND bitacora.cliente_id = empleado.centroc_cliente_id\n" +
            "AND bitacora.fecha_contrato = empleado.fecha_contrato_nogrupo\n" +
            "AND bitacora.nomina_xperiodo_id = empleado.nomina_xperiodo_id\n" +
            "WHERE bitacora.nomina_xperiodo_id = ?\n" +
            "AND bitacora.es_correcto = true";

    private static final String SELECT_LISTA_EMPLEADOS = "SELECT nomina.nomina_xperiodo_id,\n" +
            "       nomina.nombre_nomina,\n" +
            "       timbre.fecha_timbrado\n" +
            "FROM ncr_empleado_xnomina empleado\n" +
            "INNER JOIN ncr_nomina_xperiodo nomina on empleado.nomina_xperiodo_id = nomina.nomina_xperiodo_id\n" +
            "INNER JOIN ncr_timbre timbre ON empleado.nomina_xperiodo_id = timbre.nomina_xperiodo_id\n" +
            "    AND empleado.persona_id = timbre.persona_id\n" +
            "    AND empleado.centroc_cliente_id = timbre.centroc_cliente_id\n" +
            "    AND empleado.fecha_contrato_nogrupo = timbre.fecha_contrato_nogrupo\n" +
            "WHERE empleado.fecha_contrato_nogrupo = ?\n" +
            "    AND empleado.persona_id = ?\n" +
            "    AND empleado.centroc_cliente_id = ?";


    private static final String SELECT_LISTA_EMPLEADOSWITHSTATUS = "SELECT nomina.nomina_xperiodo_id,\n" +
            "       nomina.nombre_nomina,\n" +
            "       timbre.fecha_timbrado,nomina.es_extraordinaria\n" +
            "FROM ncr_empleado_xnomina empleado\n" +
            "INNER JOIN ncr_nomina_xperiodo nomina on empleado.nomina_xperiodo_id = nomina.nomina_xperiodo_id\n" +
            "INNER JOIN ncr_timbre timbre ON empleado.nomina_xperiodo_id = timbre.nomina_xperiodo_id\n" +
            "    AND empleado.persona_id = timbre.persona_id\n" +
            "    AND empleado.centroc_cliente_id = timbre.centroc_cliente_id\n" +
            "    AND empleado.fecha_contrato_nogrupo = timbre.fecha_contrato_nogrupo\n" +
            "WHERE empleado.fecha_contrato_nogrupo = ?\n" +
            "    AND empleado.persona_id = ?\n" +
            "    AND empleado.centroc_cliente_id = ?";


    private static final String SELECT_LISTA_EMPLEADOS_PERSONA_CENTRO_CLIENTE = "SELECT nomina.nomina_xperiodo_id,\n" +
            "       nomina.nombre_nomina,\n" +
            "       timbre.fecha_timbrado,nomina.es_extraordinaria\n" +
            "FROM ncr_empleado_xnomina empleado\n" +
            "INNER JOIN ncr_nomina_xperiodo nomina on empleado.nomina_xperiodo_id = nomina.nomina_xperiodo_id\n" +
            "INNER JOIN ncr_timbre timbre ON empleado.nomina_xperiodo_id = timbre.nomina_xperiodo_id\n" +
            "    AND empleado.persona_id = timbre.persona_id\n" +
            "    AND empleado.centroc_cliente_id = timbre.centroc_cliente_id\n" +
            "    AND empleado.fecha_contrato_nogrupo = timbre.fecha_contrato_nogrupo\n" +
            "    WHERE empleado.persona_id = ?\n" +
            "    AND empleado.centroc_cliente_id = ?";

    @Transactional
    public TimbradoConsulta consultaInformacionTimbrado(Integer nominaPerdiodoId, Integer personaId) throws ServiceException {
        try {
            String query = FROM;
            return jdbcOperations.prepareStatement(FROM, statement -> {
                statement.setInt(1, nominaPerdiodoId);
                statement.setInt(2, personaId);
                ResultSet resultSet = statement.executeQuery();
                return Objects.requireNonNull(jdbcOperations.entityStream(resultSet, TimbradoConsulta.class).findAny().orElse(null));
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaInformacionTimbrado " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<PercepcionConsulta> consultaPercepcion(Integer nominaPerdiodoId, Integer personaId) throws ServiceException {
        try {
            return jdbcOperations.prepareStatement(SELECT_PERCEPCIONES + FROM_PERCEPCIONES, statement -> {
                statement.setInt(1, nominaPerdiodoId);
                statement.setInt(2, personaId);
                statement.setInt(3, nominaPerdiodoId);
                statement.setInt(4, personaId);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, PercepcionConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaPercepcion " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<DeduccionConsulta> consultaDecepcion(Integer nominaPerdiodoId, Integer personaId) throws ServiceException {
        try {
            return jdbcOperations.prepareStatement(SELECT_DEDUCCIONES + FROM_DEDUCCIONES, statement -> {
                statement.setInt(1, nominaPerdiodoId);
                statement.setInt(2, personaId);
                statement.setInt(3, nominaPerdiodoId);
                statement.setInt(4, personaId);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, DeduccionConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDecepcion " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<TimbradoErroresConsulta> consultaListaErrores(Integer nominaPerdiodoId, List<Integer> listaPersonas) throws ServiceException {
        try {
            String arreglo = StringUtils.join(listaPersonas.iterator(),",");
            return jdbcOperations.prepareStatement(SELECT_CONSULTA_ERRORES + FROM_CONSULTA_ERRORES + " AND bitacora.persona_id IN (" + arreglo + ")",
                    statement -> {
                statement.setInt(1, nominaPerdiodoId);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, TimbradoErroresConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaListaErrores " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<TimbradoResumenConsulta> consultaResumen(Integer nominaPerdiodoId) throws ServiceException {
        try {
            return jdbcOperations.prepareStatement(SELECT_RESUMEN + FROM_RESUMEN, statement -> {
                statement.setInt(1, nominaPerdiodoId);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, TimbradoResumenConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaResumen " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<EmpleadosTimbrados> listaEmpleados(Date fechaContrato, Integer personaId, Integer centrocClienteId) throws ServiceException {
        try {
            java.sql.Date fecha = new java.sql.Date(fechaContrato.getTime());
            return jdbcOperations.prepareStatement(SELECT_LISTA_EMPLEADOSWITHSTATUS, statement -> {
                statement.setDate(1, fecha);
                statement.setInt(2, personaId);
                statement.setInt(3, centrocClienteId);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, EmpleadosTimbrados.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" listaEmpleados " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<EmpleadosTimbrados> listaEmpleados(Integer personaId, Integer centrocClienteId) throws ServiceException {
        try {
            return jdbcOperations.prepareStatement(SELECT_LISTA_EMPLEADOS_PERSONA_CENTRO_CLIENTE, statement -> {
                statement.setInt(1, personaId);
                statement.setInt(2, centrocClienteId);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, EmpleadosTimbrados.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" listaEmpleados " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
