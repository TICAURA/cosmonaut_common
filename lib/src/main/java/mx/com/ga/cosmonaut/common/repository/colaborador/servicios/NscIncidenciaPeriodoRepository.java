package mx.com.ga.cosmonaut.common.repository.colaborador.servicios;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.colaborador.servicios.NscIncidenciaPeriodo;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NscIncidenciaPeriodoRepository extends CrudRepository<NscIncidenciaPeriodo, Integer> {

    void deleteByNominaPeriodoId(Integer nominaPeriodoId);

}
