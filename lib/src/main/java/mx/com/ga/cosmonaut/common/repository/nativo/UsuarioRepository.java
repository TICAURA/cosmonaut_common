package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.administracion.usuarios.CantidadUsuarios;
import mx.com.ga.cosmonaut.common.dto.administracion.usuarios.FiltradoRequest;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class UsuarioRepository {

    private final JdbcOperations jdbcOperations;

    protected UsuarioRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String WHERE = "LEFT JOIN adm_usuarioxcliente auxc ON au.usuario_id = auxc.usuario_id WHERE auxc.es_activo AND auxc.centroc_cliente_id IN (";

    private static final String PORC = "%";

    @Transactional
    public List<AdmUsuarios> filtrar(FiltradoRequest request) throws ServiceException {
        try {
            StringBuilder sb = new StringBuilder("SELECT * ")
                    .append("FROM adm_usuarios au ").append(WHERE);

            int x = 1;
            for (Integer clienteId : request.getIdClientes()) {
                sb.append(clienteId);
                if (x < request.getIdClientes().size()) {
                    sb.append(", ");
                } else {
                    sb.append(") ");
                }
                x++;
            }

            List<Object> statements = new ArrayList<>();
            if (request.getIdUsuario() != null) {
                sb.append("AND au.usuario_id = ? ");
                statements.add(request.getIdUsuario());
            }
            if (request.getNombre() != null) {
                sb.append("AND LOWER(au.nombre) ILIKE LOWER(?) ");
                statements.add(request.getNombre().concat(PORC));
            }
            if (request.getApellidoPat() != null) {
                sb.append("AND LOWER(au.apellido_pat) ILIKE LOWER(?) ");
                statements.add(request.getApellidoPat().concat(PORC));
            }
            if (request.getApellidoMat() != null) {
                sb.append("AND LOWER(au.apellido_mat) ILIKE LOWER(?) ");
                statements.add(request.getApellidoMat().concat(PORC));
            }
            /*if (request.getIdCliente() != null) {
                sb.append("AND auxc.centroc_cliente_id = ? ");
                statements.add(request.getIdCliente());
            }*/
            if (request.getEmail() != null) {
                sb.append("AND LOWER(au.email) ILIKE LOWER(?) ");
                statements.add(request.getEmail().concat(PORC));
            }
            if (request.getEsActivo() != null) {
                sb.append("AND au.es_activo = ? ");
                statements.add(request.getEsActivo());
            }
            if (request.getFechaAlta() != null) {
                sb.append("AND au.fecha_alta = ? ");
                statements.add(request.getFechaAlta());
            }

            return jdbcOperations.prepareStatement(sb.toString(), statement -> {
                int i = 1;
                for (Object obj : statements) {
                    statement.setObject(i++, obj);
                }

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, AdmUsuarios.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" filtrar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<AdmUsuarios> filtrarPaginado(FiltradoRequest request, Integer numeroRegistros, Integer pagina) throws ServiceException {
        try {
            StringBuilder sb = new StringBuilder("SELECT * ")
                    .append("FROM adm_usuarios au ").append(WHERE);

            int x = 1;
            for (Integer clienteId : request.getIdClientes()) {
                sb.append(clienteId);
                if (x < request.getIdClientes().size()) {
                    sb.append(", ");
                } else {
                    sb.append(") ");
                }
                x++;
            }

            List<Object> statements = new ArrayList<>();
            if (request.getIdUsuario() != null) {
                sb.append("AND au.usuario_id::TEXT ILIKE ? ");
                statements.add(PORC.concat(request.getIdUsuario().toString()).concat(PORC));
            }
            if (request.getNombre() != null) {
                sb.append("AND LOWER(au.nombre) ILIKE LOWER(?) ");
                statements.add(PORC.concat(request.getNombre()).concat(PORC));
            }
            if (request.getApellidoPat() != null) {
                sb.append("AND LOWER(au.apellido_pat) ILIKE LOWER(?) ");
                statements.add(PORC.concat(request.getApellidoPat()).concat(PORC));
            }
            if (request.getApellidoMat() != null) {
                sb.append("AND LOWER(au.apellido_mat) ILIKE LOWER(?) ");
                statements.add(PORC.concat(request.getApellidoMat()).concat(PORC));
            }
            if (request.getEmail() != null) {
                sb.append("AND LOWER(au.email) ILIKE LOWER(?) ");
                statements.add(PORC.concat(request.getEmail()).concat(PORC));
            }
            if (request.getEsActivo() != null) {
                sb.append("AND au.es_activo = ? ");
                statements.add(request.getEsActivo());
            }
            if (request.getFechaAlta() != null) {
                sb.append("AND au.fecha_alta::DATE = ?::DATE ");
                statements.add(request.getFechaAlta());
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
                return jdbcOperations.entityStream(resultSet, AdmUsuarios.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" filtrarPaginado " + Constantes.ERROR_EXCEPCION, e);
        }
    }


    @Transactional
    public List<CantidadUsuarios> cantidadTotal(FiltradoRequest request) throws ServiceException {
        try {
            StringBuilder sb = new StringBuilder("SELECT count(distinct au.usuario_id) as cantidad  ")
                    .append("FROM adm_usuarios au ").append(WHERE);

            int x = 1;
            for (Integer clienteId : request.getIdClientes()) {
                sb.append(clienteId);
                if (x < request.getIdClientes().size()) {
                    sb.append(", ");
                } else {
                    sb.append(") ");
                }
                x++;
            }

            List<Object> statements = new ArrayList<>();
            if (request.getIdUsuario() != null) {
                sb.append("AND au.usuario_id::TEXT ILIKE ? ");
                statements.add(PORC.concat(request.getIdUsuario().toString()).concat(PORC));
            }
            if (request.getNombre() != null) {
                sb.append("AND LOWER(au.nombre) ILIKE LOWER(?) ");
                statements.add(PORC.concat(request.getNombre()).concat(PORC));
            }
            if (request.getApellidoPat() != null) {
                sb.append("AND LOWER(au.apellido_pat) ILIKE LOWER(?) ");
                statements.add(PORC.concat(request.getApellidoPat()).concat(PORC));
            }
            if (request.getApellidoMat() != null) {
                sb.append("AND LOWER(au.apellido_mat) ILIKE LOWER(?) ");
                statements.add(PORC.concat(request.getApellidoMat()).concat(PORC));
            }
            /*if (request.getIdCliente() != null) {
                sb.append("AND auxc.centroc_cliente_id = ? ");
                statements.add(request.getIdCliente());
            }*/
            if (request.getEmail() != null) {
                sb.append("AND LOWER(au.email) ILIKE LOWER(?) ");
                statements.add(PORC.concat(request.getEmail()).concat(PORC));
            }
            if (request.getEsActivo() != null) {
                sb.append("AND au.es_activo = ? ");
                statements.add(request.getEsActivo());
            }
            if (request.getFechaAlta() != null) {
                sb.append("AND au.fecha_alta = ?::DATE ");
                statements.add(request.getFechaAlta());
            }



            return jdbcOperations.prepareStatement(sb.toString(), statement -> {
                int i = 1;
                for (Object obj : statements) {
                    statement.setObject(i++, obj);
                }

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, CantidadUsuarios.class).collect(Collectors.toList());
            });
        } catch (Exception e) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" filtrar " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
