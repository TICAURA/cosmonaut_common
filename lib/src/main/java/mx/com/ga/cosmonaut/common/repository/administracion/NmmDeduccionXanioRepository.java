package mx.com.ga.cosmonaut.common.repository.administracion;

import mx.com.ga.cosmonaut.common.entity.administracion.NmmDeduccionXanio;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NmmDeduccionXanioRepository extends CrudRepository<NmmDeduccionXanio, Integer> {

}