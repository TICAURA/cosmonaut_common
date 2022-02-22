package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoEmision;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatTipoEmisionRepository extends CrudRepository<CatTipoEmision, Integer> {

}