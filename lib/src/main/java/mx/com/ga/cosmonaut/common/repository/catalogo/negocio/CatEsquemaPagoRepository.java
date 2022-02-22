package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatEsquemaPago;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatEsquemaPagoRepository extends CrudRepository<CatEsquemaPago, Long> {

    List<CatEsquemaPago> findByEsActivoOrderByDescripcion(Boolean activo);
}