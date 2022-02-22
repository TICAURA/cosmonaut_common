package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatFacultadPoder;
import java.util.List;


@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatFacultadPoderRepository extends CrudRepository<CatFacultadPoder, Integer>{

    List<CatFacultadPoder> findByEsActivoOrderByDescripcion(Boolean activo);
    
    List<CatFacultadPoder> findByDescripcion(String descripcion);

    List<CatFacultadPoder> findByDescripcionIlikeOrderByDescripcion(String descripcion);
}