package mx.com.ga.cosmonaut.common.repository;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.BitacoraSistema;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface BitacoraSistemaRepository extends CrudRepository<BitacoraSistema, Integer>  {
}



