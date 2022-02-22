package mx.com.ga.cosmonaut.common.repository.calculo;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrBitacoraPago;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface NcrBitacoraPagoRepository extends CrudRepository<NcrBitacoraPago, Long> {

    NcrBitacoraPago findByIdProveedor(Long idProveedor);

    NcrBitacoraPago findByOperacionId(String operacionId);

    void updateByOperacionId(String operacionId, String observacion);

    void updateByOperacionId(String operacionId, Long estadoPagoId, @Nullable String observacion, @Nullable String claveRastreo, @Nullable Long idProveedor, boolean esCorrecto);

    void updateByIdProveedor(Long idProveedor, boolean esCorrecto, Long estadoPagoId,@Nullable String observacion);

    Long countByNominaXperiodoIdAndEsActual(Long nominaXperiodoId, boolean esActual);

    List<NcrBitacoraPago> findByNominaXperiodoIdAndClienteIdAndEsActual(Integer nominaPeriodoId, Integer clienteId, boolean esActual);

    List<NcrBitacoraPago> findByNominaXperiodoIdAndClienteIdAndEsActualAndEstadoPagoId(Integer nominaPeriodoId, Integer clienteId, boolean esActual, Long estadoPagoId);

    Optional<NcrBitacoraPago> findByNominaXperiodoIdAndClienteIdAndPersonaIdAndEsActual(Integer nominaPeriodoId, Integer clienteId, Integer personaId, boolean esActual);

    boolean existsByNominaXperiodoIdAndEsCorrecto(Integer nominaXperiodoId, boolean esCorrecto);

    long countByNominaXperiodoIdAndPersonaIdInListAndEstadoPagoIdInListAndEsActual(Integer nominaXperiodoId, List<Long> personaId, List<Integer> estadoPagoId, boolean esActual);

    long countByNominaXperiodoIdAndPersonaIdInListAndEstadoPagoIdNotEqualsAndEsActual(Integer nominaXperiodoId, List<Long> personaId, Integer estadoPagoId, boolean esActual);

    Long countByNominaXperiodoIdAndPersonaId(Integer nominaXperiodoId, Integer personaId);

    @Query(value = "SELECT coalesce(SUM(empleado.total_neto),0)\n" +
            "FROM ncr_bitacora_pago bitacora_pago\n" +
            "INNER JOIN ncr_empleado_xnomina empleado ON bitacora_pago.persona_id = empleado.persona_id\n" +
            "AND bitacora_pago.cliente_id = empleado.centroc_cliente_id\n" +
            "AND bitacora_pago.fecha_contrato = empleado.fecha_contrato_nogrupo\n" +
            "AND bitacora_pago.nomina_xperiodo_id = empleado.nomina_xperiodo_id\n" +
            "WHERE bitacora_pago.nomina_xperiodo_id = :nominaPeriodoId\n" +
            "AND bitacora_pago.es_correcto = true\n" +
            "AND bitacora_pago.es_actual = true", nativeQuery = true)
    Double sumaPagoNeto(Integer nominaPeriodoId);

}
