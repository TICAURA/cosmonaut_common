package mx.com.ga.cosmonaut.common.repository.calculo;

import mx.com.ga.cosmonaut.common.entity.calculo.NcrDeduccionXnomina;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrDeduccionXnominaRepository extends CrudRepository<NcrDeduccionXnomina, Integer> {

    List<NcrDeduccionXnomina> findByNmmConfiguraDeduccionConfiguraDeduccionId(Integer configuraDeduccionId);

    void deleteByNominaPorPeriodo(Integer nominaPorPeriodo);

    Long countByPersonaIdPersonaIdAndNominaPorPeriodoAndCentrocClienteIdCentrocClienteIdAndNmmConfiguraDeduccionConfiguraDeduccionId
            (Integer personaId, Integer nominaPorPeriodo, Integer centrocClienteId, Integer configuraPercepcionId);


}