package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrBitacoraTimbrado;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrBitacoraTimbradoRepository extends CrudRepository<NcrBitacoraTimbrado, Integer> {

    void updateByOperacionId(String operacionId, String observacion, boolean esCorrecto, Integer estadoTimbreId);

    void updateByNominaPeriodoIdAndFechaContratoAndClienteIdAndPersonaId(Integer nominaPeriodoId, Date fechaContrato, Integer clienteId, Integer personaId, boolean esActual);

    Long countByNominaPeriodoIdAndEsActualAndPersonaIdInListAndEstadoTimbreIdNotEquals(Integer nominaPeriodoId, boolean esActual, List<Integer> lista,Integer estadoTimbreId);

    Long countByNominaPeriodoIdAndEsActualAndPersonaIdInListAndEstadoTimbreId(Integer nominaPeriodoId, boolean esActual, List<Integer> lista,Integer estadoTimbreId);

    NcrBitacoraTimbrado findByOperacionId(String operacionId);

    Optional<NcrBitacoraTimbrado> findByNominaPeriodoIdAndClienteIdAndPersonaIdAndEsActual(Integer nominaPeriodoId, Integer clienteId, Integer personaId, boolean esActual);

    @Query("SELECT * FROM ncr_bitacora_timbre WHERE es_actual = true AND es_correcto = true AND nomina_xperiodo_id = :nominaXperiodoId ")
    Set<NcrBitacoraTimbrado> findByNominaPeriodoIdAndExitoso(Integer nominaXperiodoId);

    List<NcrBitacoraTimbrado> findByNominaPeriodoIdAndClienteIdAndEsActual(Integer nominaPeriodoId, Integer clienteId, boolean esActual);

    List<NcrBitacoraTimbrado> findByNominaPeriodoIdAndClienteIdAndEsActualAndEstadoTimbreId(Integer nominaPeriodoId, Integer clienteId, boolean esActual, Integer estadoTimbreId);


    @Query(value = "SELECT coalesce(SUM(empleado.total_neto),0)\n" +
            "FROM ncr_bitacora_timbre bitacora_timbre\n" +
            "INNER JOIN ncr_empleado_xnomina empleado ON bitacora_timbre.persona_id = empleado.persona_id\n" +
            "    AND bitacora_timbre.cliente_id = empleado.centroc_cliente_id\n" +
            "    AND bitacora_timbre.fecha_contrato = empleado.fecha_contrato_nogrupo\n" +
            "    AND bitacora_timbre.nomina_xperiodo_id = empleado.nomina_xperiodo_id\n" +
            "WHERE bitacora_timbre.nomina_xperiodo_id = :nominaPeriodoId\n" +
            "AND bitacora_timbre.es_correcto = true\n" +
            "AND bitacora_timbre.es_actual = true", nativeQuery = true)
    Double sumaPagoNeto(Integer nominaPeriodoId);

}
