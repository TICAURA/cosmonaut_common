package mx.com.ga.cosmonaut.common.repository.administracion.noticias;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.dto.administracion.noticias.AdmNoticiasConsulta;
import mx.com.ga.cosmonaut.common.dto.consultas.ColaboradorGrupoNominaConsulta;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.util.Constantes;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class AdmNoticiasNativoRepository {
    private final JdbcOperations jdbcOperations;

    protected AdmNoticiasNativoRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Transactional
    public List<AdmNoticiasConsulta> consultarClientesAdministradorCosmonaut() throws ServiceException {
        try {
            String query = "select avc.centroc_cliente_id , ncc.nombre ,ncc.razon_social from  adm_version_cosmonautxcliente avc inner  join ncl_centroc_cliente ncc   \n" +
                    "on ncc.centroc_cliente_id  = avc.centroc_cliente_id \n" +
                    "where avc.version_cosmonaut_id  = 1\n" +
                    "order by avc.centroc_cliente_id";
            return jdbcOperations.prepareStatement(query, statement -> {

                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, AdmNoticiasConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +"consultaGrupoNominaPersona " + Constantes.ERROR_EXCEPCION, e);
        }
    }



    @Transactional
    public List<AdmNoticiasConsulta> consultarEmpresaByCliente(Integer clienteId) throws ServiceException {
        try {
            String query = "select centroc_cliente_id,nombre,razon_social from ncl_centroc_cliente where centro_costos_centroc_cliente_id = ?";
            return jdbcOperations.prepareStatement(query, statement -> {
                statement.setInt(1,clienteId);
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, AdmNoticiasConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +"consultaGrupoNominaPersona " + Constantes.ERROR_EXCEPCION, e);
        }
    }

    @Transactional
    public List<AdmNoticiasConsulta> consultarTodosClientes() throws ServiceException {
        try {
            String query = "select centroc_cliente_id,nombre,razon_social from ncl_centroc_cliente where centro_costos_centroc_cliente_id is null";
            return jdbcOperations.prepareStatement(query, statement -> {
                ResultSet resultSet = statement.executeQuery();
                return jdbcOperations.entityStream(resultSet, AdmNoticiasConsulta.class).collect(Collectors.toList());
            });
        }catch (Exception e){
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +"consultaGrupoNominaPersona " + Constantes.ERROR_EXCEPCION, e);
        }
    }
}
