package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrBitacoraNomina;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrBitacoraNominaRepository extends CrudRepository<NcrBitacoraNomina, Integer> {

    void deleteByNominaPeriodoId(Integer nominaXperiodoId);

    boolean existsByNominaPeriodoId(Integer nominaXperiodoId);

    void updateByNominaPeriodoId(Integer nominaXperiodoId, boolean esActual);

}
