package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.cliente.NclPuesto;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;
import mx.com.ga.cosmonaut.common.dto.NclPuestoDto;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class AbstractPuestoRepository {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractPuestoRepository.class);
    private final JdbcOperations jdbcOperations;

    private static final String QUERY = "select np.*,ncc.razon_social, na.area_id, na.descripcion from ncl_puesto np join ncl_centroc_cliente ncc on ncc.centroc_cliente_id = np.centroc_cliente_id  ";

    protected AbstractPuestoRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    public List<NclPuestoDto> consultaPuestos() throws ServiceException {
        try {
            String finalQuery = QUERY + "join ncl_puesto_xarea npx on npx.puesto_id = np.puesto_id join ncl_area na on na.area_id = npx.area_id";
            return jdbcOperations.prepareStatement(finalQuery, statement -> {

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NclPuestoDto.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<NclPuestoDto> consultaPuestosId(Integer id) throws ServiceException {
        try {
            String finalQuery = QUERY + " and np.puesto_id = ? join ncl_puesto_xarea npx on npx.puesto_id = np.puesto_id join ncl_area na on na.area_id = npx.area_id and np.es_activo=true";
            return jdbcOperations.prepareStatement(finalQuery, statement -> {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NclPuestoDto.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<NclPuestoDto> consultaPuestosxEmpresa(Integer id) throws ServiceException {
        try {
            String finalQuery = QUERY + " and ncc.centroc_cliente_id = ? join ncl_puesto_xarea npx on npx.puesto_id = np.puesto_id join ncl_area na on na.area_id = npx.area_id and np.es_activo = true";
            return jdbcOperations.prepareStatement(finalQuery, statement -> {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NclPuestoDto.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public boolean consultaPuestosxEmpresaArea(Integer centro, Integer area, String puesto) throws ServiceException {
        try {
            String finalQuery = "select p.* from ncl_puesto_xarea pa join ncl_puesto p on (pa.puesto_id = p.puesto_id ) where p.centroc_cliente_id = ? and pa.area_id= ? and  upper(trim(nombre_corto)) = ? and p.es_activo is true";
            return jdbcOperations.prepareStatement(finalQuery, statement -> {
                statement.setInt(1, centro);
                statement.setInt(2, area);
                statement.setString(3, puesto);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NclPuesto.class).collect(Collectors.toList()).isEmpty();
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }



    @Transactional
    public List<NclPuestoDto> consultaPuestosxEmpresaArea(Integer id, Integer idArea) throws ServiceException {
        try {
            String finalQuery = QUERY + " and ncc.centroc_cliente_id = ? join ncl_puesto_xarea npx on npx.puesto_id = np.puesto_id join ncl_area na on na.area_id = npx.area_id and np.es_activo = true and na.area_id = ?";
            return jdbcOperations.prepareStatement(finalQuery, statement -> {
                statement.setInt(1, id);
                statement.setInt(2, idArea);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NclPuestoDto.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<NclPuestoDto> obtenerPuestosxArea(Integer idArea) throws ServiceException {
        try {
            String finalQuery = QUERY + " join ncl_puesto_xarea npx on npx.puesto_id = np.puesto_id \n"
                    + "         join ncl_area na on na.area_id = npx.area_id and na.area_id = ?";
            return jdbcOperations.prepareStatement(finalQuery, statement -> {
                statement.setInt(1, idArea);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NclPuestoDto.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }
}
