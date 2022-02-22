package mx.com.ga.cosmonaut.common.repository.catalogo.ubicacion;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatDelegacionSubdelegacion;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatDelegacionSubdelegacionRepository extends CrudRepository<CatDelegacionSubdelegacion, Integer> {

    List<CatDelegacionSubdelegacion> findByEsActivoOrderByDescripcion(Boolean activo);
}
