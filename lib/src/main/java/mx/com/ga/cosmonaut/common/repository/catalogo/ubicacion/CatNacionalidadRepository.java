package mx.com.ga.cosmonaut.common.repository.catalogo.ubicacion;

import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatNacionalidad;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatNacionalidadRepository extends CrudRepository<CatNacionalidad, Long> {

    List<CatNacionalidad> findByEsActivoOrderByDescripcion(Boolean activo);

    List<CatNacionalidad> findByDescripcion(String descripcion);

    List<CatNacionalidad> findByDescripcionIlikeOrderByDescripcion(String descripcion);

}
