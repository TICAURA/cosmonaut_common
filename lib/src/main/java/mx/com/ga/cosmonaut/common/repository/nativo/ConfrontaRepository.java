package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.confronta.FiltradoRequest;
import mx.com.ga.cosmonaut.common.dto.confronta.FiltradoResponse;
import mx.com.ga.cosmonaut.common.dto.confronta.TrabajadoresDto;
import mx.com.ga.cosmonaut.common.dto.imss.IMSSFiltradoRequest;
import mx.com.ga.cosmonaut.common.dto.imss.IMSSFiltradoResponse;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class ConfrontaRepository {




    private static final Logger LOG = LoggerFactory.getLogger(ConfrontaRepository.class);
    private final JdbcOperations jdbcOperations;

    protected ConfrontaRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String SELECT = "SELECT c.id_confronta, nrp.registro_patronal, ncc.razon_social, c.anio, ce.mes_inicio AS mes";
    private static final String FROM = "FROM cft_confronta c INNER JOIN cat_emision ce ON c.id_emision = ce.mes_inicio INNER JOIN ncl_centroc_cliente ncc ON c.centroc_cliente_id = ncc.centroc_cliente_id INNER JOIN ncl_registro_patronal nrp ON c.centroc_cliente_id = nrp.centroc_cliente_id ";
    private static final String WHERE = "WHERE c.es_activo = true AND c.centroc_cliente_id = ? ";

    @Transactional
    public List<FiltradoResponse> filtrar(FiltradoRequest request) throws ServiceException {
        try {
            StringBuilder sb = new StringBuilder(SELECT).append(FROM).append(WHERE);

            List<Object> statements = new ArrayList<>();
            statements.add(request.getClienteId());
            if (request.getRegistroPatronal() != null) {
                sb.append("AND nrp.registro_patronal = ? ");
                statements.add(request.getRegistroPatronal());
            }
            if (request.getRazonSocial() != null) {
                sb.append("AND ncc.razon_social = ? ");
                statements.add(request.getRazonSocial());
            }
            if (request.getAnio() != null) {
                sb.append("AND c.anio = ? ");
                statements.add(request.getAnio());
            }
            if (request.getMes() != null) {
                sb.append("AND ce.mes_inicio = UPPER(?) ");
                statements.add(request.getMes());
            }
            sb.append("ORDER BY c.fecha_alta DESC ");

            return jdbcOperations.prepareStatement(sb.toString(), statement -> {
                int i = 1;
                for (Object obj : statements) {
                    statement.setObject(i++, obj);
                }

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, FiltradoResponse.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" filtrar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public TrabajadoresDto obtieneDatosBase() throws ServiceException {
        try {
            String num_empleado = "OT383";
            String query = "SELECT rp.registro_patronal AS registro_patronal, rp.centroc_cliente_id AS id_cliente, " +
                    "ncl.nombre AS cliente, nco.num_empleado AS numero_empleado, p.nss AS nss, " +
                    "CONCAT(p.nombre, '$', p.apellido_pat, '$', p.apellido_mat) AS nombre_trabajador " +
                    "FROM ncl_registro_patronal rp " +
                    "INNER JOIN ncl_centroc_cliente ncl ON rp.centroc_cliente_id = ncl.centroc_cliente_id " +
                    "INNER JOIN nco_contrato_colaborador nco ON ncl.centroc_cliente_id = nco.centroc_cliente_id " +
                    "INNER JOIN nco_persona p ON nco.persona_id = p.persona_id WHERE nco.num_empleado = ?" +
                    "LIMIT 1";

            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setString(1, num_empleado);

                TrabajadoresDto dto = null;
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    dto = extraerRs(rs);
                }
                return dto;
            });
        } catch (Exception e) {
            LOG.error(Constantes.EXCEPCION, e);
            throw new ServiceException(Constantes.ERROR);
        }
    }

    private TrabajadoresDto extraerRs(ResultSet rs) throws SQLException {
        TrabajadoresDto dto = new TrabajadoresDto();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int i = 1; i <= columns; i++) {
            setearColumn(rsmd.getColumnName(i), rs, dto);
        }
        return dto;
    }

    private void setearColumn(String columnName, ResultSet rs, TrabajadoresDto dto) throws SQLException {
        switch (columnName) {
            case "registro_patronal":
                dto.setRegistro_patronal(rs.getString("registro_patronal"));
                break;
            case "id_cliente":
                dto.setId_cliente(rs.getString("id_cliente"));
                break;
            case "cliente":
                dto.setCliente(rs.getString("cliente"));
                break;
            case "numero_empleado":
                dto.setNumero_empleado(rs.getString("numero_empleado"));
                break;
            case "nss":
                dto.setNss(rs.getString("nss"));
                break;
            case "nombre_trabajador":
                dto.setNombre_trabajador(rs.getString("nombre_trabajador"));
                break;
            case "tipo_movimiento":
                dto.setTipo_movimiento(rs.getString("tipo_movimiento"));
                break;
            case "fecha_movimiento":
                dto.setFecha_movimiento(rs.getString("fecha_movimiento"));
                break;
            case "integrado":
                dto.setIntegrado(rs.getString("integrado"));
                break;
        }
    }

}
