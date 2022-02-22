package mx.com.ga.cosmonaut.common.entity.colaborador;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.EmbeddedId;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrEmpleadoXnominaPK;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_empleado_xnomina")
public class EmpleadoXnomina {

    @EmbeddedId
    protected NcrEmpleadoXnominaPK ncrEmpleadoXnominaPK;

    /**
    @MappedProperty(value = "fecha_contrato_nogrupo")
    private Date fechaContratoNogrupo;

    @MappedProperty(value = "persona_id")
    private Long personaId;

    @MappedProperty(value = "nomina_xperiodo_id")
    private Long nominaXperiodoId;

     @MappedProperty(value = "centroc_cliente_id")
     private Long centrocClienteId;
*/
    @MappedProperty(value = "dias_laborados")
    private BigDecimal diasLaborados;

    @MappedProperty(value = "dias_periodo")
    private BigDecimal diasPeriodo;

    @MappedProperty(value = "dias_ausencia")
    private Long diasAusencia;

    @MappedProperty(value = "dias_incapacidad")
    private Long diasIncapacidad;

    @MappedProperty(value = "dias_vacaciones")
    private Long diasVacaciones;

    @MappedProperty(value = "dias_economicos")
    private Long diasEconomicos;

    @MappedProperty(value = "dias_festivo_labor")
    private Long diasFestivoLabor;

    @MappedProperty(value = "dias_descanso_labor")
    private Long diasDescansoLabor;

    @MappedProperty(value = "dias_descanso_labor_dom")
    private Long diasDescansoLaborDom;

    @MappedProperty(value = "dias_imss")
    private BigDecimal diasImss;

    @MappedProperty(value = "horas_extra_doble")
    private Long horasExtraDoble;

    @MappedProperty(value = "horas_extra_triple")
    private Long horasExtraTriple;

    @MappedProperty(value = "total_percepciones")
    private BigDecimal totalPercepciones;

    @MappedProperty(value = "total_deducciones")
    private BigDecimal totalDeducciones;

    @MappedProperty(value = "total_neto")
    private BigDecimal totalNeto;

    @MappedProperty(value = "tarifa_periodica_subsidio_id")
    private Long tarifaPeriodicaSubsidioId;

    @MappedProperty(value = "tarifa_periodica_isr_id")
    private Long tarifaPeriodicaIsrId;


    @MappedProperty(value = "sbc")
    private BigDecimal sbc;

    @MappedProperty(value = "provision_vacaciones")
    private BigDecimal provisionVacaciones;

    @MappedProperty(value = "provision_prima_vacacional")
    private BigDecimal provisionPrimaVacacional;

    @MappedProperty(value = "provision_aguinaldo")
    private BigDecimal provisionAguinaldo;

    @MappedProperty(value = "provision_isn")
    private BigDecimal provisionIsn;

    @MappedProperty(value = "provision_imss_patronal")
    private BigDecimal provisionImssPatronal;

    @MappedProperty(value = "fecha_pago_timbrado")
    private Timestamp fechaPagoTimbrado;

    @MappedProperty(value = "tasa_aplicable_isn_id")
    private Long tasaAplicableIsnId;

    @MappedProperty(value = "liq_fin_dias_vacaciones_proporcion")
    private BigDecimal liqFinDiasVacacionesProporcion;

    @MappedProperty(value = "liq_fin_dias_prima_vacacional_proporcion")
    private BigDecimal liqFinDiasPrimaVacacionalProporcion;

    @MappedProperty(value = "total_exento")
    private BigDecimal totalExento;

    @MappedProperty(value = "total_gravable")
    private BigDecimal totalGravable;

    @MappedProperty(value = "salario_diario")
    private BigDecimal salarioDiario;

    @MappedProperty(value = "liq_fin_dias_vacaciones_no_disfrutadas")
    private BigDecimal liqFinDiasVacacionesNoDisfrutadas;

    @MappedProperty(value = "dias_aguinaldo")
    private BigDecimal diasAguinaldo;

    @MappedProperty(value = "salario_diario_integrado")
    private BigDecimal salarioDiarioIntegrado;

    @MappedProperty(value = "total_sdi")
    private BigDecimal totalSdi;

    @MappedProperty(value = "total_sdi_fijo")
    private BigDecimal totalSdiFijo;

    @MappedProperty(value = "total_sdi_variable")
    private BigDecimal totalSdiVariable;

    @MappedProperty(value = "estado_pago_id")
    private Long estadoPagoId;

    @MappedProperty(value = "estado_timbre_id")
    private Long estadoTimbreId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_antiguedad")
    private Date fechaAntiguedad;

    @MappedProperty(value = "dias_reales_laborados")
    private Long diasRealesLaborados;

}
