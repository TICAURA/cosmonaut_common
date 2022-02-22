package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.NclGrupoNominaDto;
import mx.com.ga.cosmonaut.common.dto.SqlDto;
import mx.com.ga.cosmonaut.common.dto.consultas.GrupoNominaConsulta;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.Sql;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class GrupoNominaRepository {

    private final JdbcOperations jdbcOperations;

    protected GrupoNominaRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT = "SELECT ngn.pago_complementario AS pago_complementario, ngn.grupo_nomina_id id, ngn.nombre AS nombre, ncc.razon_social AS razon, cpp.descripcion AS periodo, (SELECT count(ncxn.persona_id) FROM nco_contrato_colaborador ncxn WHERE ncxn.centroc_cliente_id = ? AND ncxn.grupo_nomina_id = ngn.grupo_nomina_id) AS numero ";
    private static final String SELECT_DINAMICO = "SELECT ngn.pago_complementario AS pago_complementario, ngn.grupo_nomina_id id, ngn.nombre AS nombre, ncc.razon_social AS razon, cpp.descripcion AS periodo, (SELECT count(ncxn.persona_id) FROM nco_contrato_colaborador ncxn WHERE ncxn.centroc_cliente_id = ? AND ncxn.grupo_nomina_id = ngn.grupo_nomina_id AND ncxn.es_activo) AS numero ";

    @Transactional
    public List<GrupoNominaConsulta> consultaListaGrupoNomina(Integer id) throws ServiceException {
        try {
            SqlDto sqlDto = new SqlDto();
            sqlDto.setListaDatos(new ArrayList<>());
            StringBuilder query = new StringBuilder(200);
            query.append(SELECT).append("FROM ncl_grupo_nomina ngn ")
            .append("INNER JOIN  ncl_centroc_cliente ncc on ngn.centroc_cliente_id = ncc.centroc_cliente_id ")
            .append("INNER JOIN cs_periodicidad_pago cpp on ngn.periodicidad_pago_id = cpp.periodicidad_pago_id ")
            .append("WHERE ngn.centroc_cliente_id = ? ")
            .append("AND ngn.es_activo = ?");
            sqlDto.setQuery(query.toString());
            sqlDto.getListaDatos().add(id);
            sqlDto.getListaDatos().add(id);
            sqlDto.getListaDatos().add(Constantes.ESTATUS_ACTIVO);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, GrupoNominaConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +"consultaListaGrupoNomina " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<GrupoNominaConsulta> consultaDimanicaGrupoNomina(NclGrupoNominaDto grupoNomina) throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamicoGrupoNomina(grupoNomina);
            sqlDto.setQuery(Sql.eliminarUltimoAnd(sqlDto.getQuery()));
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, GrupoNominaConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +"consultaDimanicaGrupoNomina " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private SqlDto generaQueryDinamicoGrupoNomina(NclGrupoNominaDto grupoNomina){
        SqlDto sqlDto = new SqlDto();
        sqlDto.setListaDatos(new ArrayList<>());
        StringBuilder query = new StringBuilder(100);
        query.append(SELECT_DINAMICO).append("FROM ncl_grupo_nomina ngn ")
        .append("INNER JOIN  ncl_centroc_cliente ncc on ngn.centroc_cliente_id = ncc.centroc_cliente_id ")
        .append("INNER JOIN cs_periodicidad_pago cpp on ngn.periodicidad_pago_id = cpp.periodicidad_pago_id ")
        /**.append("INNER JOIN cat_esquema_pago cep on ngn.esquema_pago_id = cep.esquema_pago_id ")*/
        .append("WHERE ngn.es_activo = " + Constantes.ESTATUS_ACTIVO + " ")
        .append("AND ");
        if (grupoNomina.getCentrocClienteId() != null
                && grupoNomina.getCentrocClienteId().getCentrocClienteId() != null){
            query.append(" ncc.centroc_cliente_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(grupoNomina.getCentrocClienteId().getCentrocClienteId());
            sqlDto.getListaDatos().add(grupoNomina.getCentrocClienteId().getCentrocClienteId());
        }
        if (grupoNomina.getPeriodicidadPagoId() != null
                && !grupoNomina.getPeriodicidadPagoId().getPeriodicidadPagoId().isEmpty()){
            query.append(" ngn.periodicidad_pago_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(grupoNomina.getPeriodicidadPagoId().getPeriodicidadPagoId());
        }

        if (grupoNomina.getNombre() != null && !grupoNomina.getNombre().isEmpty()){
            query.append(" ngn.nombre ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + grupoNomina.getNombre() + "%");
        }
        if (grupoNomina.getCentrocClienteId() != null
                && grupoNomina.getCentrocClienteId().getRazonSocial() != null
                && !grupoNomina.getCentrocClienteId().getRazonSocial().isEmpty()){
            query.append(" ncc.razon_social ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + grupoNomina.getCentrocClienteId().getRazonSocial() + "%");
        }


    /**
        if (grupoNomina.getEsquemaPagoId() != null && grupoNomina.getEsquemaPagoId().getEsquemaPagoId() != null){
            query.append(" cep.esquema_pago_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(grupoNomina.getEsquemaPagoId().getEsquemaPagoId());
        }
     */

        sqlDto.setQuery(query.toString());
        return sqlDto;
    }

}
