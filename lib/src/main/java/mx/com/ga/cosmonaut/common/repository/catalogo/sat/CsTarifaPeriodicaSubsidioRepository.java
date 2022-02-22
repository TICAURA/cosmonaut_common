package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTarifaPeriodicaSubsidio;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsTarifaPeriodicaSubsidioRepository extends CrudRepository<CsTarifaPeriodicaSubsidio, Integer> {

    List<CsTarifaPeriodicaSubsidio> findByEsActivo(Boolean activo);
    
    @Join(value="periodicidadPagoId", alias="pp")
    List<CsTarifaPeriodicaSubsidio> findByEsActivoAndPeriodicidadPagoIdPeriodicidadPagoIdOrderBytarifaPeriodicaSubsidioId(Boolean esActivo, String periodicidadPagoId);

    @Query("SELECT * FROM cs_tarifa_periodica_subsidio ctps WHERE ctps.fecha_fin < NOW()::DATE AND ctps.es_activo = :esActivo " +
            "AND ctps.periodicidad_pago_id = :periodicidadPagoId ORDER BY ctps.tarifa_periodica_subsidio_id ")
    List<CsTarifaPeriodicaSubsidio> findByEsActivoAndPeriodicidadPagoIdPeriodicidadPagoIdOrderBytarifaPeriodicaSubsidioIdEspecial(
            Boolean esActivo, String periodicidadPagoId);

    @Query(value = "SELECT *\n" +
            "FROM cs_tarifa_periodica_subsidio\n" +
            "WHERE periodicidad_pago_id = :periodicidadPagoId\n" +
            "AND :fechaInicio BETWEEN fecha_inicio AND fecha_fin\n" +
            "AND :limiteInferior BETWEEN limite_inferior and limite_superior",nativeQuery = true)
    CsTarifaPeriodicaSubsidio findByPeriodicidadPagoIdAndFechaInicioAndLimiteInferior(String periodicidadPagoId, Date fechaInicio, BigDecimal limiteInferior);

    @Query(value = "SELECT *\n" +
            "FROM cs_tarifa_periodica_subsidio\n" +
            "WHERE periodicidad_pago_id = :periodicidadPagoId\n" +
            "AND :fechaInicio BETWEEN fecha_inicio AND fecha_fin\n" +
            "AND :limiteInferior BETWEEN limite_inferior and limite_superior",nativeQuery = true)
    Optional<CsTarifaPeriodicaSubsidio> findByPeriodicidadPagoIdAndFechaInicioAndLimiteInferiorOptional(String periodicidadPagoId, Date fechaInicio, BigDecimal limiteInferior);

    @Query("SELECT * FROM cs_tarifa_periodica_subsidio ctps WHERE ctps.es_activo = :esActivo " +
            "AND ctps.periodicidad_pago_id = :periodicidadPagoId ORDER BY ctps.tarifa_periodica_subsidio_id ")
    List<CsTarifaPeriodicaSubsidio> findByEsActivoAndPeriodicidadPagoIdPeriodicidadPagoIdOrderBytarifaPeriodicaSubsidio(Boolean esActivo, String periodicidadPagoId);

    @Query(value = "SELECT COUNT(*) FROM cs_tarifa_periodica_subsidio subsidio \n" +
            "WHERE (:fechaInicio BETWEEN subsidio.fecha_inicio AND subsidio.fecha_fin \n" +
            "OR :fechaFin BETWEEN subsidio.fecha_inicio AND subsidio.fecha_fin) \n" +
            "AND subsidio.periodicidad_pago_id = :periodicidadPagoId \n" +
            "AND es_activo = true", nativeQuery = true)
    Long findByFechaInicioBetweenAndFechaFinBetweenAndPeriodicidadPagoId(Date fechaInicio, Date fechaFin, String periodicidadPagoId);

    @Query(value = "SELECT *\n" +
            "FROM cs_tarifa_periodica_subsidio\n" +
            "WHERE periodicidad_pago_id in (:listaPeriodicidadPagoId)\n" +
            "AND :fechaInicio BETWEEN fecha_inicio AND fecha_fin\n",nativeQuery = true)
    List<CsTarifaPeriodicaSubsidio> existsByIdPeriodicidadPagoIdAndFechaInicio(List<String> listaPeriodicidadPagoId, Date fechaInicio);

    @Query(value = "SELECT *\n" +
            "FROM cs_tarifa_periodica_subsidio\n" +
            "WHERE periodicidad_pago_id = :periodicidadPagoId \n" +
            "AND (:fechaFin BETWEEN fecha_inicio AND fecha_fin OR :fechaInicio BETWEEN fecha_inicio AND fecha_fin)\n" +
            "AND (:limiteInferior BETWEEN limite_inferior AND limite_superior OR :limiteSuperior BETWEEN limite_inferior AND limite_superior) ",nativeQuery = true)
    List<CsTarifaPeriodicaSubsidio> existsByIdPeriodicidadPagoIdAndFechaInicioAndLimites(String periodicidadPagoId,Date fechaInicio,Date fechaFin,Double limiteInferior,Double limiteSuperior,Double montoSubsidio);


}