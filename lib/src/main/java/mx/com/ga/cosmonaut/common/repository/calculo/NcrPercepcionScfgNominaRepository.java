package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrPercepcionScfgNomina;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrPercepcionScfgNominaRepository extends CrudRepository<NcrPercepcionScfgNomina, Integer> {

    void deleteByNominaPeriodoId(Integer nominaPeriodoId);

}
