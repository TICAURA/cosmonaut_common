package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.cliente.NclPolitica;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclPoliticaRepository extends CrudRepository<NclPolitica, Integer> {

    @Query(value = "SELECT * FROM ncl_politica np WHERE np.centroc_cliente_id = :id AND politica_id = :idPolitica", nativeQuery = true)
    List<NclPolitica> consultaPoliticaEmpresaId(Integer id, Integer idPolitica);
        
    @Query(value = "SELECT * FROM ncl_politica np WHERE np.centroc_cliente_id = :idCliente and np.nombre = :nombre", nativeQuery = true)
    List<NclPolitica> obtienePoliticaXEmpresaNombre(Integer idCliente, String nombre);
    
    @Query(value = "SELECT * FROM ncl_politica np WHERE np.centroc_cliente_id = :idCliente and np.es_estandar = :esEstandar", nativeQuery = true)
    List<NclPolitica> obtienePoliticaXEmpresaNombreEstandar(Integer idCliente, Boolean esEstandar);

    NclPolitica findByCentrocClienteIdCentrocClienteIdAndEsEstandarAndEsActivo(Integer centrocClienteId, boolean esEstandar, boolean esActivo);
    
    void update(@Id Integer politicaId, boolean esActivo);
    
    NclPolitica findByCentrocClienteIdCentrocClienteIdAndEsEstandar(Integer centrocClienteId, Boolean esEstandar);

    List<NclPolitica> findByEsActivoOrderByNombre(Boolean activo);

    List<NclPolitica> findByEsActivoAndCentrocClienteIdCentrocClienteIdOrderByPoliticaId(
            boolean activo,Integer centroCClienteId);

    boolean existsByPoliticaIdAndCentrocClienteIdCentrocClienteId(Integer politicaId, Integer centrocClienteId);

    @Query(value = "SELECT * FROM ncl_politica np WHERE np.centroc_cliente_id = :idCliente and upper(np.nombre) = :nombre and np.es_activo is true", nativeQuery = true)
    List<NclPolitica> existsByCentrocClienteIdCentrocClienteIdAndNombreAndEsActivo(Integer idCliente, String nombre);

}