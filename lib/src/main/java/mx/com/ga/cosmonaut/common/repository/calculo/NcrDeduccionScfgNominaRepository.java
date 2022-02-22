package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrDeduccionScfgNomina;

import java.util.Date;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrDeduccionScfgNominaRepository extends CrudRepository<NcrDeduccionScfgNomina, Integer> {

    NcrDeduccionScfgNomina findByPersonaIdAndFechaContratoNogrupoAndnominaPeriodoIdAndCentrocClienteIdAnd(Integer personaId, Date fechaContratoNoGrupo, Integer nominaPeriodoId, Integer centrocClienteId, String tipoDeduccionId);

    void deleteByNominaPeriodoId(Integer nominaPeriodoId);

    List<NcrDeduccionScfgNomina> findByNominaPeriodoId(Integer nominaPeriodoId);

}
