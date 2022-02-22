package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsPeriodicidadPago;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTarifaPeriodicaIsr;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsTarifaPeriodicaIsrRepository extends CrudRepository<CsTarifaPeriodicaIsr, Integer> {

    List<CsTarifaPeriodicaIsr> findByEsActivo(Boolean activo);

    List<CsTarifaPeriodicaIsr> findByEsActivoAndPeriodicidadPagoId(Boolean activo, CsPeriodicidadPago periodicidadPagoId);
    
    @Join(value="periodicidadPagoId", alias="pp")
    List<CsTarifaPeriodicaIsr> findByEsActivoAndPeriodicidadPagoIdPeriodicidadPagoIdOrderByTarifaPeriodicaIsrId(Boolean esActivo, String periodicidadPagoId);

    @Query("SELECT * FROM cs_tarifa_periodica_isr ctpi WHERE ctpi.fecha_fin < NOW()::DATE AND ctpi.es_activo = :esActivo " +
            "AND ctpi.periodicidad_pago_id = :periodicidadPagoId ORDER BY ctpi.tarifa_periodica_isr_id ")
    List<CsTarifaPeriodicaIsr> findByEsActivoAndPeriodicidadPagoIdPeriodicidadPagoIdOrderByTarifaPeriodicaIsrIdEspecial(
            Boolean esActivo, String periodicidadPagoId);

    @Query(value = "SELECT * FROM cs_tarifa_periodica_isr WHERE periodicidad_pago_id = :periodicidadPagoId AND :fechaInicio between fecha_inicio and fecha_fin " +
            "AND :limiteInferior between limite_inferior and limite_superior", nativeQuery = true)
    CsTarifaPeriodicaIsr findByPeriodicidadPagoAndFechaInicioAndLimiteInferior(String periodicidadPagoId, Date fechaInicio, BigDecimal limiteInferior);

    @Query(value = "SELECT * FROM cs_tarifa_periodica_isr WHERE periodicidad_pago_id = :periodicidadPagoId AND :fechaInicio between fecha_inicio and fecha_fin " +
            "AND :limiteInferior between limite_inferior and limite_superior", nativeQuery = true)
    Optional<CsTarifaPeriodicaIsr> findByPeriodicidadPagoAndFechaInicioAndLimiteInferiorOptional(String periodicidadPagoId, Date fechaInicio, BigDecimal limiteInferior);

    @Query("SELECT * FROM cs_tarifa_periodica_isr ctpi WHERE ctpi.es_activo = :esActivo " +
            "AND ctpi.periodicidad_pago_id = :periodicidadPagoId ORDER BY ctpi.tarifa_periodica_isr_id ")
    List<CsTarifaPeriodicaIsr> findByEsActivoAndPeriodicidadPagoIdPeriodicidadPagoIdOrderByTarifaPeriodicaIsr(Boolean esActivo, String periodicidadPagoId);

    @Query(value = "SELECT COUNT(*) FROM cs_tarifa_periodica_isr tarifa \n" +
            "WHERE (:fechaInicio BETWEEN tarifa.fecha_inicio\n" +
            "AND tarifa.fecha_fin or :fechaFin BETWEEN tarifa.fecha_inicio AND tarifa.fecha_fin) \n" +
            "AND tarifa.periodicidad_pago_id = :periodicidadPagoId \n" +
            "AND es_activo = true", nativeQuery = true)
    Long findByFechaInicioBetweenAndFechaFinBetweenAndPeriodicidadPagoId(Date fechaInicio, Date fechaFin, String periodicidadPagoId);

    @Query(value = "SELECT * FROM cs_tarifa_periodica_isr \n" +
            "WHERE periodicidad_pago_id in (:listaPeriodicidadPagoId) \n" +
            "AND :fechaInicio between fecha_inicio and fecha_fin ", nativeQuery = true)
    List<CsTarifaPeriodicaIsr> existsByIdPeriodicidadPagoAndFechaInicio(List<String>listaPeriodicidadPagoId, Date fechaInicio);

    @Query(value = "SELECT * \n" +
            "FROM cs_tarifa_periodica_isr\n" +
            "WHERE (periodicidad_pago_id = :periodicidadPagoId\n" +
            "AND cuota_fija = :cuotaFija \n" +
            "AND :limiteInferior BETWEEN limite_inferior AND limite_superior) \n" +
            "OR (periodicidad_pago_id = :periodicidadPagoId\n" +
            "AND cuota_fija = :cuotaFija \n" +
            "AND :limiteSuperior BETWEEN limite_inferior AND limite_superior)\n" +
            "OR (periodicidad_pago_id = :periodicidadPagoId\n" +
            "AND cuota_fija = :cuotaFija \n" +
            "AND :fechaInicio BETWEEN fecha_inicio AND fecha_fin)\n" +
            "OR (periodicidad_pago_id = :periodicidadPagoId\n" +
            "AND cuota_fija = :cuotaFija \n" +
            "AND :fechaFin BETWEEN fecha_inicio AND fecha_fin) ", nativeQuery = true)
    List<CsTarifaPeriodicaIsr> existsByIdPeriodicidadPagoAndFechaInicioAndLimite(String periodicidadPagoId,Date fechaInicio,Date fechaFin,Double limiteInferior,Double limiteSuperior,Double cuotaFija);


}