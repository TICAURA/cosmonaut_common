package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsEstado;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsEstadoRepository extends CrudRepository<CsEstado, String> {

}