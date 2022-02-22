package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrProvision;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrProvisionRepository extends CrudRepository<NcrProvision, Integer> {

    void deleteByNominaPeriodoId(Integer nominaPeriodoId);

}
