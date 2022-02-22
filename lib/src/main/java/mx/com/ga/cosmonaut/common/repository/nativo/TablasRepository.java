package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.TablasExtDto;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;
import mx.com.ga.cosmonaut.common.dto.TablasDto;
import mx.com.ga.cosmonaut.common.dto.TablasValoresDto;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class TablasRepository {

    private static final Logger LOG = LoggerFactory.getLogger(TablasRepository.class);
    private final JdbcOperations jdbcOperations;

    protected TablasRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    public List<TablasValoresDto> obtieneTablasEdo() throws ServiceException {
        try {
            String query = "select ce.d_estado as tabla, limite_inferior as limiteInferior, limite_superior as limiteSuperior,\n" +
"               cuota_fija as cuotaFija, tasa as excedente, *\n" +
"              from cat_tasa_aplicable_isn ctai inner join cat_estado ce on ctai.c_estado = ce.c_estado\n" +
"             order by ce.c_estado, limite_inferior, limite_superior asc";
            return jdbcOperations.prepareStatement(query, statement -> {
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, TablasValoresDto.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<TablasDto> obtieneCatPeriodoPago() throws ServiceException {
        try {
            String query = "select *, cpp.periodicidad_pago_id as periodo, cpp.descripcion as tabla from cs_periodicidad_pago cpp where periodicidad_pago_id in('02','03','04','05','10')";
            return jdbcOperations.prepareStatement(query, statement -> {
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, TablasDto.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<TablasExtDto> obtieneCatPeriodoPagoACT() throws ServiceException {
        try {
            String query = "select *, cpp.periodicidad_pago_id as periodo, cpp.descripcion as tabla, cpp.es_activo esActivo from cs_periodicidad_pago cpp where periodicidad_pago_id in('02','03','04','05','10')";
            return jdbcOperations.prepareStatement(query, statement -> {
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, TablasExtDto.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<TablasExtDto> obtieneCatPeriodoPagoSubsidioACT() throws ServiceException {
        try {
            String query = "select *,cpp.periodicidad_pago_id as periodo, cpp.descripcion as tabla, cpp.es_activo esActivo from cs_periodicidad_pago cpp where periodicidad_pago_id in('02','03','04','05','10')";
            return jdbcOperations.prepareStatement(query, statement -> {
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, TablasExtDto.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    @Transactional
    public List<TablasDto> obtieneCatPeriodoPagoSubsidio() throws ServiceException {
        try {
            String query = "select *,cpp.periodicidad_pago_id as periodo, cpp.descripcion as tabla from cs_periodicidad_pago cpp where periodicidad_pago_id in('02','03','04','05','10')";
            return jdbcOperations.prepareStatement(query, statement -> {
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, TablasDto.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

}
