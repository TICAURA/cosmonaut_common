package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.SqlDto;
import mx.com.ga.cosmonaut.common.dto.consultas.IncidenciasConsulta;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.Sql;
import mx.com.ga.cosmonaut.common.util.Validar;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class IncidenciasRepository {

    private final JdbcOperations jdbcOperations;
    protected IncidenciasRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT = "SELECT persona.nombre AS nombre, persona.apellido_pat AS apellido_paterno, persona.apellido_mat AS apellido_materno,persona.persona_id AS persona_id, "
            + "colaborador.num_empleado AS numero_empleado,colaborador.centroc_cliente_id AS cliente_id,colaborador.fecha_contrato AS fecha_contrato, "
            + "catincidencia.tipo_incidencia_id AS tipo_incidencia_id,incidencias.he_tiempo,incidencias.unidad_medida_id, catincidencia.descripcion AS incidencia_descripcion, "
            + "incidencias.monto AS monto, incidencias.fecha_aplicacion AS fecha_aplicacion, incidencias.incidencia_id AS incidencia_id, incidencias.fecha_inicio AS fecha_inicio, incidencias.fecha_fin AS fecha_fin, incidencias.he_tiempo AS tiempo,incidencias.comentarios AS comentarios,incidencias.comentario_acepta_rechaza AS comentario_acepta,incidencias.es_activo AS es_activo,incidencias.duracion AS duracion, "
            + "politica.politica_id AS politica_id,politica.nombre AS politica_nombre, "
            + "estado.estado_incidencia_id AS estado_id,estado.descripcion AS estado_descripcion, "
            + "area.area_id AS area_id,area.descripcion AS area_descripcion ";

    @Transactional
    public List<IncidenciasConsulta> consultaClienteId(Integer id) throws ServiceException {
        try {
            SqlDto sqlDto = new SqlDto();
            sqlDto.setListaDatos(new ArrayList<>());
            StringBuilder query = new StringBuilder(200);
            query.append(SELECT).append(generaFrom())
                    .append("WHERE colaborador.centroc_cliente_id = ? AND incidencias.es_activo = ? ");
            return incidenciasConsultas(id, sqlDto, query);
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaListaIncidencias " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<IncidenciasConsulta> consultaIncidenciaId(Integer incidenciaId) throws ServiceException {
        try {
            SqlDto sqlDto = new SqlDto();
            sqlDto.setListaDatos(new ArrayList<>());
            StringBuilder query = new StringBuilder(200);
            query.append(SELECT).append(generaFrom())
                    .append("WHERE incidencias.incidencia_id = ? AND incidencias.es_activo = ? ");
            return incidenciasConsultas(incidenciaId, sqlDto, query);
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaObtenerId " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private List<IncidenciasConsulta> incidenciasConsultas(Integer incidenciaId, SqlDto sqlDto, StringBuilder query) {
        sqlDto.setQuery(query.toString());
        sqlDto.getListaDatos().add(incidenciaId);
        sqlDto.getListaDatos().add(Constantes.ESTATUS_ACTIVO);
        return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
            ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
            return jdbcOperations.entityStream(resultado, IncidenciasConsulta.class).collect(Collectors.toList());
        });
    }

    @Transactional
    public List<IncidenciasConsulta> consultaDimanicaIncidencia(IncidenciasConsulta incidencia) throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamicoIncidencias(incidencia);
            sqlDto.setQuery(Sql.eliminarUltimoAnd(sqlDto.getQuery()));
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, IncidenciasConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDimanicaIncidencia " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<IncidenciasConsulta> consultaDimanicaIncidenciaPaginado(IncidenciasConsulta incidencia,Integer numeroRegistros, Integer pagina) throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamicoIncidencias(incidencia);
            sqlDto.setQuery(Sql.eliminarUltimoAnd(sqlDto.getQuery()));
            StringBuilder query = new StringBuilder(100);
            query.append(sqlDto.getQuery()).append(" LIMIT ? ").append(" OFFSET ? ");
            sqlDto.setQuery(query.toString());
            sqlDto.getListaDatos().add(numeroRegistros);
            sqlDto.getListaDatos().add(pagina);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, IncidenciasConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDimanicaIncidenciaPaginado " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<IncidenciasConsulta> consultalistaClienteIdFechaInicioFechaFin(Long clienteId, Date fechaInicio, Date fechaFin)
            throws ServiceException {
        try {
            SqlDto sqlDto = new SqlDto();
            sqlDto.setListaDatos(new ArrayList<>());
            StringBuilder query = new StringBuilder(200);
            query.append(SELECT).append(generaFrom())
                    .append("WHERE incidencias.cliente_id = ? ")
                    .append("AND incidencias.fecha_inicio BETWEEN ? ")
                    .append("AND ? ");
            sqlDto.setQuery(query.toString());
            sqlDto.getListaDatos().add(clienteId);
            sqlDto.getListaDatos().add(fechaInicio);
            sqlDto.getListaDatos().add(fechaFin);
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {
                ResultSet resultado = Sql.ejecucionQuery(sqlDto, statement);
                return jdbcOperations.entityStream(resultado, IncidenciasConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultalistaClienteIdFechaInicioFechaFin " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    private String generaFrom(){
        StringBuilder query = new StringBuilder(200);
        query.append("FROM nsc_incidencia incidencias ")
                .append("INNER JOIN nco_contrato_colaborador colaborador ON incidencias.fecha_contrato = colaborador.fecha_contrato ")
                .append("AND incidencias.persona_id = colaborador.persona_id ")
                .append("AND incidencias.cliente_id = colaborador.centroc_cliente_id ")
                .append("INNER JOIN nco_persona persona ON colaborador.persona_id = persona.persona_id ")
                .append("INNER JOIN cat_tipo_incidencia catincidencia on incidencias.tipo_incidencia_id = catincidencia.tipo_incidencia_id ")
                .append("INNER JOIN ncl_politica politica on colaborador.politica_id = politica.politica_id ")
                .append("INNER JOIN cat_estado_incidencia estado on incidencias.ultimo_estado_incidencia_id = estado.estado_incidencia_id ")
                .append("INNER JOIN ncl_area area on colaborador.area_id = area.area_id ");
        return query.toString();
    }

    private SqlDto generaQueryDinamicoIncidencias(IncidenciasConsulta incidencia){
        SqlDto sqlDto = new SqlDto();
        sqlDto.setListaDatos(new ArrayList<>());
        StringBuilder query = new StringBuilder(100);
        query.append(SELECT).append(generaFrom()).append("WHERE ");

        if (incidencia.getClienteId() != null){
            query.append(" colaborador.centroc_cliente_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(incidencia.getClienteId());
        }

        if (incidencia.getTipoIncidenciaId() != null){
            query.append(" incidencias.tipo_incidencia_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(incidencia.getTipoIncidenciaId());
        }

        if (Validar.validaTexto(incidencia.getNombre())){
            query.append(" persona.nombre ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + incidencia.getNombre() + "%");
        }

        if (Validar.validaTexto(incidencia.getApellidoPaterno())){
            query.append(" persona.apellido_pat ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + incidencia.getApellidoPaterno() + "%");
        }

        if (Validar.validaTexto(incidencia.getApellidoMaterno())){
            query.append(" persona.apellido_mat ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + incidencia.getApellidoMaterno() + "%");
        }

        if (Validar.validaTexto(incidencia.getNumeroEmpleado())){
            query.append(" colaborador.num_empleado ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add("%" + incidencia.getNumeroEmpleado() + "%");
        }

        if (incidencia.getAreaId() != null){
            query.append(" area.area_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(incidencia.getAreaId());
        }

        if (incidencia.getEsActivo() != null){
            query.append(" incidencias.es_activo = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(incidencia.getEsActivo());
        }

        if (incidencia.getFechaInicio() != null && incidencia.getFechaFin() != null){
            query.append(" incidencias.fecha_inicio BETWEEN ? AND ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(incidencia.getFechaInicio());
            sqlDto.getListaDatos().add(incidencia.getFechaFin());
        }

        sqlDto.setQuery(query.toString());
        return sqlDto;
    }

}
