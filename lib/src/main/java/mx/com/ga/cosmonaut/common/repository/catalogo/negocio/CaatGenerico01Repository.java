package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CaatGenerico01;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CaatGenerico01Repository extends CrudRepository<CaatGenerico01, Long> {

}