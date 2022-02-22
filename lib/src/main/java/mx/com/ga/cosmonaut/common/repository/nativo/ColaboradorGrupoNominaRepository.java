package mx.com.ga.cosmonaut.common.repository.nativo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.consultas.ColaboradorGrupoNominaConsulta;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;


import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class ColaboradorGrupoNominaRepository {

     private final JdbcOperations jdbcOperations;

    protected ColaboradorGrupoNominaRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    public List<ColaboradorGrupoNominaConsulta> consultaGrupoNominaPersona(Integer id) throws ServiceException {
        try {
            String query = "SELECT " +
                    "np.nombre AS nombre, " +
                    "np.apellido_pat AS apellido_paterno, " +
                    "np.apellido_mat AS apellido_materno, " +
                    "ncc.num_empleado AS numero_empleado, " +
                    "n.razon_social AS razon_social " +
                    "FROM ncl_colaborador_xgrupo_nomina ncg " +
                    "INNER JOIN nco_persona np on ncg.persona_id = np.persona_id " +
                    "INNER JOIN nco_contrato_colaborador ncc on ncg.persona_id = ncc.persona_id " +
                    "INNER JOIN ncl_centroc_cliente n on ncc.centroc_cliente_id = n.centroc_cliente_id " +
                    "WHERE ncg.grupo_nomina_id = ?";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, ColaboradorGrupoNominaConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +"consultaGrupoNominaPersona " + Constantes.ERROR_EXCEPCION, e);
        }
    }

}
