package mx.com.ga.cosmonaut.common.repository.cliente;

import mx.com.ga.cosmonaut.common.entity.cliente.NclRepresentanteLegal;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NclRepresentanteLegalRepository extends CrudRepository<NclRepresentanteLegal, Integer> {

}