package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.cliente.NclPuesto;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclPuestoRepository extends CrudRepository<NclPuesto, Integer> {
    
     @Query(value = "select nombre_corto from ncl_puesto where puesto_id = :puestoId", nativeQuery = true)
    String obtieneDescripcionPuesto(Integer puestoId);

    @Query(value = "select  p.* from ncl_puesto_xarea pa join ncl_puesto p on (pa.puesto_id  = p.puesto_id) " +
            "join ncl_area a on (a.area_id= pa.area_id) where a.centroc_cliente_id = :centro and a.area_id = :area and p.puesto_id = :puesto and a.es_activo is true", nativeQuery = true)
    List<NclPuesto> obtieneDescripcionPuestoArea(Integer centro,Integer area, Integer puesto);

    @Query(value = "select  p.* from ncl_puesto_xarea pa join ncl_puesto p on (pa.puesto_id  = p.puesto_id) " +
            "join ncl_area a on (a.area_id= pa.area_id) where a.centroc_cliente_id = :centro and a.area_id = :area and a.es_activo is true", nativeQuery = true)
    List<NclPuesto> obtieneDescripcionPuestoAreaSinPuesto(Integer centro,Integer area);

    @Query(value = "select  p.* from ncl_puesto_xarea pa join ncl_puesto p on (pa.puesto_id  = p.puesto_id) " +
            "join ncl_area a on (a.area_id= pa.area_id) where a.centroc_cliente_id = :centro and a.area_id = :area and a.es_activo is true", nativeQuery = true)
    List<NclPuesto> obtieneAreaPuesto(Integer centro,Integer area);

     List<NclPuesto> findByEsActivoOrderByDescripcion(Boolean activo);

     List<NclPuesto> findByEsActivoAndCentrocClienteIdCentrocClienteIdOrderByPuestoId(
             boolean activo,Integer centrocClienteId);

    boolean existsByPuestoIdAndCentrocClienteIdCentrocClienteId(Integer puestoId, Integer centrocClienteId);

}