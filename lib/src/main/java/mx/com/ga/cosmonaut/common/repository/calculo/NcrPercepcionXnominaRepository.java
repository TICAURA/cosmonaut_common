package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrPercepcionXnomina;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrPercepcionXnominaRepository extends CrudRepository<NcrPercepcionXnomina, Integer> {
    
    List<NcrPercepcionXnomina> findByNmmConfiguraPercepcionConfiguraPercepcionId(Integer configuraPercepcionId);

    void deleteByNominaPorPeriodo(Integer nominaPorPeriodo);

    Long countByPersonaIdPersonaIdAndNominaPorPeriodoAndCentrocClienteIdCentrocClienteIdAndNmmConfiguraPercepcionConfiguraPercepcionId
            (Integer personaId, Integer nominaPorPeriodo, Integer centrocClienteId, Integer configuraPercepcionId);

    @Query(value = "SELECT * FROM ncr_percepcion_xnomina percepcion\n" +
            "INNER JOIN ncr_nomina_xperiodo nomina ON percepcion.nomina_xperiodo_id = nomina.nomina_xperiodo_id\n" +
            "WHERE :fecha BETWEEN nomina.fecha_inicio AND nomina.fecha_fin\n" +
            "AND percepcion.configura_percepcion_id = :configuraPercepcionId ",nativeQuery = true)
    List<NcrPercepcionXnomina> findByFecha(Date fecha, Integer configuraPercepcionId);
    
}