package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.SqlDto;
import mx.com.ga.cosmonaut.common.dto.consultas.PagoComplementarioConsulta;
import mx.com.ga.cosmonaut.common.dto.reportes.PagoComplementario;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.Sql;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static mx.com.ga.cosmonaut.common.util.ConstantesReportes.ES_CERO;
import static mx.com.ga.cosmonaut.common.util.ConstantesReportes.ES_VACIO;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class PagoComplementarioRepository {

    private final JdbcOperations jdbcOperations;

    protected PagoComplementarioRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    public List<PagoComplementarioConsulta> consultaDimanica(PagoComplementario pagoComplementario) throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamico(pagoComplementario);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, PagoComplementarioConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDimanica " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private SqlDto generaQueryDinamico(PagoComplementario pagoComplementario) {

        SqlDto sqlDto = new SqlDto();
        sqlDto.setListaDatos(new ArrayList<>());
        StringBuilder query = new StringBuilder(100);


        query.append("select np.persona_id, " +
                "ncc2.nombre as nombre_empresa, " +
                "ngn.nombre as grupo_nomina, " +
                "ncc.num_empleado as numero_empleado," +
                "concat(np.nombre,' ',np.apellido_pat ,' ',np.apellido_mat) as nombre_empleado, " +
                "ncc.ppp_monto_complementario as pago_complementario " +
                "from nco_contrato_colaborador ncc " +
                "Inner JOIN (SELECT persona_id,MAX(fecha_contrato) fecha  " +
                "FROM nco_contrato_colaborador  " +
                "GROUP BY persona_id) colaborador_fecha   " +
                "ON ncc.fecha_contrato = colaborador_fecha.fecha AND ncc.persona_id = colaborador_fecha.persona_id " +
                "inner join ncl_grupo_nomina ngn on (ncc.grupo_nomina_id = ngn.grupo_nomina_id) " +
                "inner join ncl_centroc_cliente ncc2 on (ncc2.centroc_cliente_id = ncc.centroc_cliente_id) " +
                "inner join nco_persona np on (ncc.persona_id = np.persona_id) " +
                "where ngn.pago_complementario = true AND ");

        /** empresa. */
        if (!pagoComplementario.getIdEmpresa().equals(ES_CERO)) {
            pagoComplementario.setIdEmpresaActual(pagoComplementario.getIdEmpresa());
        }

        query.append(" ncc.centroc_cliente_id = ? ");
        sqlDto.getListaDatos().add(pagoComplementario.getIdEmpresaActual());
        query.append(Constantes.SQL_AND);

        /** nombre. */
        if(!ES_VACIO.equalsIgnoreCase(pagoComplementario.getNombreEmpleado())) {
            query.append(" np.nombre ilike ? ");
            sqlDto.getListaDatos().add("%" + pagoComplementario.getNombreEmpleado() +"%");
            query.append(Constantes.SQL_AND);
        }

        /** primer apellido. */
        if(!ES_VACIO.equalsIgnoreCase(pagoComplementario.getPrimerApellidoEmpleado())) {
            query.append(" np.apellido_pat ilike ? ");
            sqlDto.getListaDatos().add("%" + pagoComplementario.getPrimerApellidoEmpleado() +"%");
            query.append(Constantes.SQL_AND);
        }

        /** segundo apellido. */
        if(!ES_VACIO.equalsIgnoreCase(pagoComplementario.getSegundoApellidoEmpleado())) {
            query.append(" np.apellido_mat ilike ? ");
            sqlDto.getListaDatos().add("%" + pagoComplementario.getSegundoApellidoEmpleado() +"%");
            query.append(Constantes.SQL_AND);
        }

        /** grupo nomina. */
        if(!pagoComplementario.getGrupoNomina().equals(ES_CERO)) {
            query.append(" ngn.grupo_nomina_id  = ? ");
            sqlDto.getListaDatos().add(pagoComplementario.getGrupoNomina());
            query.append(Constantes.SQL_AND);
        }

        /** numero empleado. */
        if(!ES_VACIO.equalsIgnoreCase(pagoComplementario.getNumeroEmpleado())) {
            query.append(" ncc.num_empleado  = ? ");
            sqlDto.getListaDatos().add(pagoComplementario.getNumeroEmpleado());
            query.append(Constantes.SQL_AND);
        }

        sqlDto.setQuery(Sql.eliminarUltimoAnd(query.toString()));
        sqlDto.setQuery(sqlDto.getQuery() + " ORDER BY np.persona_id DESC ");
        return sqlDto;
    }
}
