package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.consultas.ResultadoRepetidos;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import mx.com.ga.cosmonaut.common.dto.SqlDto;
import mx.com.ga.cosmonaut.common.entity.cliente.NclAreaXEmpresa;
import mx.com.ga.cosmonaut.common.entity.cliente.NclEmpleadoXArea;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPuestoXColaborador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class AbstractAreaRepository {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractAreaRepository.class);
    private final JdbcOperations jdbcOperations;

    protected AbstractAreaRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    public List<NclEmpleadoXArea> consultaEmpleadoXArea(Integer id) throws ServiceException {
        try {
            String query = "select na.*, ncc.*, count(ncc2.area_id) from ncl_area na\n"
                    + "join ncl_centroc_cliente ncc on na.centroc_cliente_id = ncc.centroc_cliente_id and ncc.centroc_cliente_id = ? and na.es_activo = true\n"
                    + "left join nco_contrato_colaborador ncc2 on ncc2.centroc_cliente_id = ncc.centroc_cliente_id\n"
                    + "and ncc2.area_id = na.area_id and ncc2.centroc_cliente_id = na.centroc_cliente_id\n"
                    + "group by na.area_id, ncc.centroc_cliente_id order by na.area_id";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NclEmpleadoXArea.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<NclAreaXEmpresa> consultaAreaXEmpresa(Integer id) throws ServiceException {
        try {
            String query = "select na.*, ncc.*  from ncl_area na Join ncl_centroc_cliente ncc on na.centroc_cliente_id = ncc.centroc_cliente_id and na.centroc_cliente_id = ? and na.es_activo = true group by na.area_id, ncc.centroc_cliente_id";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NclAreaXEmpresa.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }


    @Transactional
    public ResultadoRepetidos consultaAreaXEmpresaRepetida(Integer idCliente, String nombre) throws ServiceException {
        try {
            String query = "select  count(na.nombre_corto) as resultado  from ncl_area na Join ncl_centroc_cliente ncc on na.centroc_cliente_id = ncc.centroc_cliente_id and na.centroc_cliente_id = ? and upper(na.nombre_corto) =  ? and  na.es_activo is true";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, idCliente);
                statement.setString(2, nombre.toUpperCase());
                ResultSet resultSet = statement.executeQuery();
                return Objects.requireNonNull(jdbcOperations.entityStream(resultSet, ResultadoRepetidos.class).findAny().orElse(null));

            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }


    @Transactional
    public List<NclAreaXEmpresa> consultaAreaXEmpresaDescripcion(Integer id, String descripcion) throws ServiceException {
        try {
            String query = "select na.*, ncc.*  from ncl_area na Join ncl_centroc_cliente ncc on na.centroc_cliente_id = ncc.centroc_cliente_id\n "
                    + "and na.centroc_cliente_id = ? and na.es_activo = true and na.descripcion = ? group by na.area_id, ncc.centroc_cliente_id";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, id);
                statement.setString(2, descripcion);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NclAreaXEmpresa.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<NcoPuestoXColaborador> obtenerPuestoXEmpleado(Integer id, Integer idArea) throws ServiceException {
        try {
            String query = "SELECT persona.nombre,\n" +
                    "       persona.apellido_pat,\n" +
                    "       persona.apellido_mat,\n" +
                    "       colaborador.num_empleado,\n" +
                    "       puesto.puesto_id,\n" +
                    "       area.area_id,\n" +
                    "       area.descripcion as area,\n" +
                    "       puesto.descripcion as puesto,\n" +
                    "       cliente.centroc_cliente_id,\n" +
                    "       cliente.razon_social\n" +
                    "FROM nco_contrato_colaborador colaborador\n" +
                    " INNER JOIN (SELECT persona_id,MAX(fecha_inicio) fecha FROM nco_contrato_colaborador GROUP BY persona_id) colaborador_fecha \n" +
                    " ON colaborador.fecha_inicio = colaborador_fecha.fecha AND colaborador.persona_id = colaborador_fecha.persona_id \n" +
                    "INNER JOIN ncl_centroc_cliente cliente ON colaborador.centroc_cliente_id = cliente.centroc_cliente_id\n" +
                    "INNER JOIN nco_persona persona ON colaborador.persona_id = persona.persona_id\n" +
                    "INNER JOIN ncl_area area ON colaborador.area_id = area.area_id\n" +
                    "INNER JOIN ncl_puesto puesto ON colaborador.puesto_id = puesto.puesto_id\n" +
                    "INNER JOIN cat_tipo_persona tipo_persona ON persona.tipo_persona_id = tipo_persona.tipo_persona_id\n" +
                    "WHERE cliente.centroc_cliente_id = ?\n" +
                    "AND area.area_id = ?\n" +
                    "AND colaborador.es_activo = true" ;
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, id);
                statement.setInt(2, idArea);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NcoPuestoXColaborador.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<NclEmpleadoXArea> consultaAreaXEmpresaDinamica(NclEmpleadoXArea nclEmpleadoXArea) throws ServiceException {
        try {
            SqlDto sqlDto = generaQueryDinamicoArea(nclEmpleadoXArea);
            sqlDto.setQuery(eliminarUltimoAnd(sqlDto.getQuery()));
            StringBuilder query = new StringBuilder(sqlDto.getQuery());
            query.append(" left join nco_contrato_colaborador ncc2 on ncc2.centroc_cliente_id = ncc.centroc_cliente_id ");
            query.append(" and ncc2.area_id = na.area_id and ncc2.centroc_cliente_id = na.centroc_cliente_id ");
            query.append(" group by na.area_id, ncc.centroc_cliente_id order by na.area_id");
            sqlDto.setQuery(query.toString());
            return jdbcOperations.prepareStatement(sqlDto.getQuery(), statement -> {

                int i = 1;
                for (Object dato : sqlDto.getListaDatos()) {
                    if (dato instanceof Integer) {
                        statement.setInt(i, (Integer) dato);
                    }
                    else if (dato instanceof String) {
                        statement.setString(i, (String) dato);
                    }
                    i++;
                }
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NclEmpleadoXArea.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    private SqlDto generaQueryDinamicoArea(NclEmpleadoXArea nclEmpleadoXArea) {

        SqlDto sqlDto = new SqlDto();
        sqlDto.setListaDatos(new ArrayList<>());
        StringBuilder query = new StringBuilder(300);
        query.append("SELECT na.*, ncc.*, count(ncc2.area_id) FROM").append(" ncl_area na ").append(Constantes.SQL_INNER_JOIN).
                append(" ncl_centroc_cliente ncc ").append(Constantes.SQL_ON).
                append(" na.centroc_cliente_id = ncc.centroc_cliente_id ").append(Constantes.SQL_AND).
                append(" na.es_activo = true ").append(Constantes.SQL_AND);

        if (nclEmpleadoXArea.getNclCentrocCliente() != null
                && nclEmpleadoXArea.getNclCentrocCliente().getCentrocClienteId() != null) {
            query.append(" na.centroc_cliente_id = ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(nclEmpleadoXArea.getNclCentrocCliente().getCentrocClienteId());
        }

        if (nclEmpleadoXArea.getNombreCorto() != null
                && !nclEmpleadoXArea.getNombreCorto().isEmpty()) {
            query.append(" na.nombre_corto ILIKE ? ");
            query.append(Constantes.SQL_AND);
            sqlDto.getListaDatos().add(nclEmpleadoXArea.getNombreCorto() + "%");
        }

        sqlDto.setQuery(query.toString());
        return sqlDto;
    }

    public static String eliminarUltimoAnd(String query) {
        int i;
        query = query.trim();
        i = query.lastIndexOf(Constantes.ESPACIO);
        if (i != -1) {
            query = query.substring(0, i);
        } else {
            query = "";
        }
        return query;
    }

}
