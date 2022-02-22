package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.consultas.DomicilioConsulta;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class DomicilioRepository {

    private final JdbcOperations jdbcOperations;

    protected DomicilioRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT = "SELECT domicilio.domicilio_id AS domicilio_id, domicilio.d_codigo AS codigo_postal, estado.d_estado AS entidad_federativa, municipio.d_mnpio AS municipio, asentamiento.d_asenta AS colonia, domicilio.calle AS calle, domicilio.num_exterior AS numero_exterior, domicilio.num_interior AS numero_interior, null AS sede_id, null AS sede_nombre ";
    private static final String SELECT_SEDE = "SELECT domicilio.domicilio_id AS domicilio_id, domicilio.d_codigo AS codigo_postal, estado.d_estado AS entidad_federativa, municipio.d_mnpio AS municipio, asentamiento.d_asenta AS colonia, domicilio.calle AS calle, domicilio.num_exterior AS numero_exterior, domicilio.num_interior AS numero_interior, sede.sede_id AS sede_id, sede.descripcion AS sede_nombre ";
    private static final String FROM = "FROM nma_domicilio domicilio ";
    private static final String INNER = "INNER JOIN cat_asentamiento asentamiento ON domicilio.c_estado = asentamiento.c_estado " +
            "AND domicilio.d_codigo = asentamiento.d_codigo " +
            "AND domicilio.c_mnpio = asentamiento.c_mnpio " +
            "AND domicilio.id_asenta_cpcons = asentamiento.id_asenta_cpcons " +
            "INNER JOIN cat_municipio municipio on asentamiento.c_mnpio = municipio.c_mnpio and asentamiento.c_estado = municipio.c_estado " +
            "INNER JOIN cat_estado estado ON municipio.c_estado = estado.c_estado ";

    @Transactional
    public List<DomicilioConsulta> consultaDomicilioEmleado(Integer id) throws ServiceException {
        try {
            StringBuilder query = new StringBuilder(200);
            query.append(SELECT).append(FROM).append(INNER).
                    append("WHERE persona_id = ?");

            return jdbcOperations.prepareStatement(query.toString(), statement -> {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, DomicilioConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDomicilioEmleado " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<DomicilioConsulta> consultaDomicilioEmpresaId(Integer empresaId) throws ServiceException {
        try {
            StringBuilder query = new StringBuilder(200);
            query.append(SELECT_SEDE).append(FROM).append(INNER).
                    append("INNER JOIN ncl_sede sede on domicilio.sede_id = sede.sede_id ").
                    append("WHERE domicilio.centroc_cliente_id = ? ").
                    append("AND sede.es_activo = ? ");

            return jdbcOperations.prepareStatement(query.toString(), statement -> {
                statement.setInt(1, empresaId);
                statement.setBoolean(2, Constantes.ESTATUS_ACTIVO);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, DomicilioConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDomicilioEmpresaId " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<DomicilioConsulta> consultaDomicilioSedeId(Integer sedeId) throws ServiceException {
        try {
            StringBuilder query = new StringBuilder(200);
            query.append(SELECT_SEDE).append(FROM).
                    append(INNER).
                    append("INNER JOIN ncl_sede sede on domicilio.sede_id = sede.sede_id ").
                    append("WHERE domicilio.sede_id = ? ");

            return jdbcOperations.prepareStatement(query.toString(), statement -> {
                statement.setInt(1, sedeId);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, DomicilioConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" consultaDomicilioSedeId " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
