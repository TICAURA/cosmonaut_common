package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.consultas.ResultadoBoolean;
import mx.com.ga.cosmonaut.common.dto.consultas.ResultadoRepetidos;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import mx.com.ga.cosmonaut.common.dto.EmpleadosXJornada;
import mx.com.ga.cosmonaut.common.dto.Jornada;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class JornadasRepository {

    private static final Logger LOG = LoggerFactory.getLogger(JornadasRepository.class);
    private final JdbcOperations jdbcOperations;

    protected JornadasRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    public List<Jornada> consultaJornadaXEmpresa(Integer idCliente, Integer idJornada) throws ServiceException {
        try {
            String query = "select ctj.descripcion, ctj.tipo_jornada_id, nj.*,  count(ncc2.de_jornada_id) \n" +
                    "                   from ncl_jornada nj\n" +
                    "                   join ncl_centroc_cliente ncc on nj.centroc_cliente_id = ncc.centroc_cliente_id\n" +
                    "                   and ncc.centroc_cliente_id = ? and nj.es_activo = true and nj.jornada_id = ?\n" +
                    "                   inner join cs_tipo_jornada ctj on ctj.tipo_jornada_id = nj.tipo_jornada_id \n" +
                    "                   left join nco_contrato_colaborador ncc2 on ncc2.centroc_cliente_id = ncc.centroc_cliente_id \n" +
                    "                   and ncc2.de_jornada_id = nj.jornada_id and ncc2.centroc_cliente_id = nj.centroc_cliente_id \n" +
                    "                   group by nj.jornada_id , ncc.razon_social, nj.tipo_jornada_id, ctj.descripcion, ctj.tipo_jornada_id";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, idCliente);
                statement.setInt(2, idJornada);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, Jornada.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<EmpleadosXJornada> consultaEmpleadosJornadaEmpresa(Integer idCliente, Integer idJornada) throws ServiceException {
        try {
            String query = "SELECT persona.nombre, persona.apellido_pat AS apellidop, persona.apellido_mat AS apellidom,colaborador.num_empleado AS numeroemp, jornada.jornada_id, puesto.descripcion AS puesto\n" +
                    "FROM nco_contrato_colaborador colaborador\n" +
                    " INNER JOIN (SELECT persona_id,MAX(fecha_inicio) fecha FROM nco_contrato_colaborador GROUP BY persona_id) colaborador_fecha \n" +
                    " ON colaborador.fecha_inicio = colaborador_fecha.fecha AND colaborador.persona_id = colaborador_fecha.persona_id \n" +
                    "LEFT JOIN nco_persona persona on colaborador.persona_id = persona.persona_id\n" +
                    "INNER JOIN ncl_jornada jornada on jornada.jornada_id = colaborador.de_jornada_id\n" +
                    "INNER JOIN ncl_puesto puesto on puesto.puesto_id = colaborador.puesto_id \n" +
                    "AND colaborador.centroc_cliente_id = ?\n" +
                    "AND jornada.jornada_id = ?\n" +
                    "AND puesto.es_activo = true\n" +
                    "AND jornada.es_activo = true\n" +
                    "AND colaborador.es_activo = true";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, idCliente);
                statement.setInt(2, idJornada);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, EmpleadosXJornada.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }
    
     @Transactional
    public List<Jornada> obtieneJornadasXEmpresa(Integer idCliente) throws ServiceException {
        try {
            String query =  "select ctj.descripcion,  ctj.tipo_jornada_id, nj.*,  count(ncc2.de_jornada_id) \n" +
                    "                   from ncl_jornada nj\n" +
                    "                   join ncl_centroc_cliente ncc on nj.centroc_cliente_id = ncc.centroc_cliente_id\n" +
                    "                   and ncc.centroc_cliente_id = ? and nj.es_activo = true\n" +
                    "                   inner join cs_tipo_jornada ctj on ctj.tipo_jornada_id = nj.tipo_jornada_id \n" +
                    "                   left join nco_contrato_colaborador ncc2 on ncc2.centroc_cliente_id = ncc.centroc_cliente_id\n" +
                    "                   and ncc2.de_jornada_id = nj.jornada_id and ncc2.centroc_cliente_id = nj.centroc_cliente_id\n" +
                    "                   group by nj.jornada_id , ncc.razon_social, nj.tipo_jornada_id, ctj.descripcion, ctj.tipo_jornada_id";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, idCliente);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, Jornada.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }


    @Transactional
    public ResultadoRepetidos obtieneJornadasXEmpresaRepetidos(Integer idCliente, String nombre) throws ServiceException {
        try {
            String query = "select count(ncc.centroc_cliente_id) as resultado from ncl_jornada nj join ncl_centroc_cliente ncc on nj.centroc_cliente_id = ncc.centroc_cliente_id  and ncc.centroc_cliente_id = ? and upper(nj.nombre) = ? and nj.es_activo = true ";
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



}
