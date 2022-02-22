package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatSubcontratista;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatSubcontratistaRepository extends CrudRepository<CatSubcontratista, Long> {
    List<CatSubcontratista> findByEsActivoOrderByDescripcion(Boolean activo);
}