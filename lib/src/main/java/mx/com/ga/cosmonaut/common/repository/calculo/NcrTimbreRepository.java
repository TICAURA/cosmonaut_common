package mx.com.ga.cosmonaut.common.repository.calculo;

import mx.com.ga.cosmonaut.common.entity.calculo.NcrTimbre;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrTimbreRepository extends CrudRepository<NcrTimbre, Integer> {

    Optional<NcrTimbre> findByNominaPeriodoIdAndCentrocClienteIdAndPersonaIdAndEsActual(Integer nominaPeriodoId, Integer centrocClienteId, Integer personaId, boolean esActual);


}