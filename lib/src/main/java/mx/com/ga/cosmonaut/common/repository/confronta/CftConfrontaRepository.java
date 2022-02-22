package mx.com.ga.cosmonaut.common.repository.confronta;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.confronta.CftConfronta;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CftConfrontaRepository extends CrudRepository<CftConfronta, Long> {



}
