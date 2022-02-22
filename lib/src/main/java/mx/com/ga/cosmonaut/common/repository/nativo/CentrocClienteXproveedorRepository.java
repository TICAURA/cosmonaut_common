package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.cliente.FiltrarRequest;
import mx.com.ga.cosmonaut.common.dto.cliente.FiltrarReponse;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class CentrocClienteXproveedorRepository {

    private final JdbcOperations jdbcOperations;

    protected CentrocClienteXproveedorRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT = "SELECT nccxp.centroc_cliente_xproveedor_id, ncc2.centroc_cliente_id AS cliente_id, ncc2.nombre AS cliente, ncc.centroc_cliente_id AS empresa_id, ncc.nombre AS empresa, ncc.rfc ";
    private static final String FROM = "FROM ncl_centroc_cliente_xproveedor nccxp INNER JOIN ncl_centroc_cliente ncc ON nccxp.centroc_cliente_id = ncc.centroc_cliente_id LEFT JOIN ncl_centroc_cliente ncc2 ON ncc.centro_costos_centroc_cliente_id = ncc2.centroc_cliente_id ";
    private static final String WHERE = "WHERE 1=1 ";

    @Transactional
    public List<FiltrarReponse> filtrar(FiltrarRequest request) throws ServiceException {
        try {
            StringBuilder sb = new StringBuilder(SELECT).append(FROM).append(WHERE);

            List<Object> statements = new ArrayList<>();
            if (request.getClienteId() != null) {
                sb.append("AND ncc2.centroc_cliente_id = ? ");
                statements.add(request.getClienteId());
            }
            if (request.getEmpresaId() != null) {
                sb.append("AND nccxp.centroc_cliente_id = ? ");
                statements.add(request.getEmpresaId());
            }

            return jdbcOperations.prepareStatement(sb.toString(), statement -> {
                int i = 1;
                for (Object obj : statements) {
                    statement.setObject(i++, obj);
                }

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, FiltrarReponse.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" filtrar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<FiltrarReponse> filtrarPaginado(FiltrarRequest request, Integer numeroRegistros, Integer pagina) throws ServiceException {
        try {
            StringBuilder sb = new StringBuilder(SELECT).append(FROM).append(WHERE);

            List<Object> statements = new ArrayList<>();
            if (request.getClienteId() != null) {
                sb.append("AND ncc2.centroc_cliente_id = ? ");
                statements.add(request.getClienteId());
            }
            if (request.getEmpresaId() != null) {
                sb.append("AND nccxp.centroc_cliente_id = ? ");
                statements.add(request.getEmpresaId());
            }

            sb.append(" LIMIT ? ");
            statements.add(numeroRegistros);

            sb.append(" OFFSET ? ");
            statements.add(pagina);

            return jdbcOperations.prepareStatement(sb.toString(), statement -> {
                int i = 1;
                for (Object obj : statements) {
                    statement.setObject(i++, obj);
                }

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, FiltrarReponse.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" filtrar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
