package mx.com.ga.cosmonaut.common.repository.cliente;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.cliente.NclPuestoXarea;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclPuestoXareaRepository extends CrudRepository<NclPuestoXarea, Integer> {

 
    @Query(value= "select * from ncl_puesto_xarea where area_id = :idArea", nativeQuery=true)
    List<NclPuestoXarea> obtenerXId(Integer idArea);
    
    Integer update(@Id Integer id, Boolean esActivo);

    boolean existsByAreaIdAndPuestoId(Integer areaId, Integer puestoId);
}