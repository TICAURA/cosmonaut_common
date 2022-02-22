package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;

import mx.com.ga.cosmonaut.common.dto.nomina.historica.FiltradoRequest;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrNominaXperiodo;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class NominasHistoricasRepository {

    private final JdbcOperations jdbcOperations;

    protected NominasHistoricasRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String PORC = "%";

    private static final String SELECT = "SELECT nnxp.* ";
    private static final String FROM = "FROM ncr_nomina_xperiodo nnxp ";
    private static final String WHERE = "WHERE 1=1 ";

    @Transactional
    public List<NcrNominaXperiodo> filtrado(FiltradoRequest request) throws ServiceException {
        try {
            StringBuilder sb = new StringBuilder(SELECT).append(FROM).append(WHERE);
            List<Object> statements = new ArrayList<>();

            if (request.getNombre() != null) {
                sb.append("AND LOWER(nnxp.nombre_nomina) LIKE LOWER(?) ");
                statements.add(request.getNombre().concat(PORC));
            }
            if (request.getClavePeriodo() != null) {
                sb.append("AND LOWER(nnxp.clave_periodo) LIKE LOWER(?) ");
                statements.add(request.getClavePeriodo().concat(PORC));
            }
            if (request.getFechaInicio() != null) {
                sb.append("AND nnxp.fecha_inicio = ?::DATE ");
                statements.add(request.getFechaInicio());
            }

            return jdbcOperations.prepareStatement(sb.toString(), statement -> {
                int i = 1;
                for (Object obj : statements) {
                    statement.setObject(i++, obj);
                }

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, NcrNominaXperiodo.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" filtrado " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
