package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.cliente.NclArea;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclAreaRepository extends CrudRepository<NclArea, Integer> {
    

    @Query(value = "select * from ncl_area where area_id = :areaId", nativeQuery = true)
    Optional<NclArea> findByAreaId(Integer areaId);

    @Query(value = "select * from ncl_area where descripcion = :descripcion", nativeQuery = true)
    List<NclArea> findByDescripcion(String descripcion);
    
    @Query(value = "select * from ncl_area" , nativeQuery = true)
    List<NclArea> findAll();
    
    @Query(value = "select nombre_corto from ncl_area where area_id = :areaId", nativeQuery = true)
    String obtieneDescripcionArea(Integer areaId);
    
    void update(@Id Integer areaId, boolean esActivo);

    List<NclArea> findByEsActivoOrderByDescripcion(Boolean activo);

    List<NclArea> findByEsActivoAndCentrocClienteIdOrderByAreaId(
            boolean activo,Integer centrocClienteId);

    boolean existsByAreaIdAndCentrocClienteId(Integer areaId, Integer centrocClienteId);

}