package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrDeduccionImss;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrDeduccionImssRepository extends CrudRepository<NcrDeduccionImss, Integer> {

    void deleteByDeduccionScfgNominaId(Integer deduccionScfgNominaId);

}
