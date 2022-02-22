package mx.com.ga.cosmonaut.common.repository.calculo;

import mx.com.ga.cosmonaut.common.entity.calculo.NcrEstadoTimbre;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrEstadoTimbreRepository extends CrudRepository<NcrEstadoTimbre, Integer> {

}