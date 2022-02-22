package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatCalculoAntiguedad;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatCalculoAntiguedadxRepository extends CrudRepository<CatCalculoAntiguedad, Short> {

    List<CatCalculoAntiguedad> findByEsActivoOrderByDescripcion(Boolean activo);
}
