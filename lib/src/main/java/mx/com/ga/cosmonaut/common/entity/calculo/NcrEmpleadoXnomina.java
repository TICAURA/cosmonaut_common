package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.Relation;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_empleado_xnomina")
public class NcrEmpleadoXnomina implements Serializable {

    private static final long serialVersionUID = 1L;

   /** @EmbeddedId
    protected NcrEmpleadoXnominaPK ncrEmpleadoXnominaPK;
    */
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato_nogrupo")
    private Date fechaContrato;
    @MappedProperty(value = "dias_laborados")
    private BigDecimal diasLaborados;
    @MappedProperty(value = "dias_periodo")
    private Double diasPeriodo;
    @MappedProperty(value = "dias_ausencia")
    private BigDecimal diasAusencia;
    @MappedProperty(value = "dias_incapacidad")
    private Integer diasIncapacidad;
    @MappedProperty(value = "dias_vacaciones")
    private Integer diasVacaciones;
    @MappedProperty(value = "dias_economicos")
    private Integer diasEconomicos;
    @MappedProperty(value = "dias_festivo_labor")
    private Integer diasFestivoLabor;
    @MappedProperty(value = "dias_descanso_labor")
    private Integer diasDescansoLabor;
    @MappedProperty(value = "dias_descanso_labor_dom")
    private Integer diasDescansoLaborDom;
    @MappedProperty(value = "dias_imss")
    private BigDecimal diasImss;
    @MappedProperty(value = "horas_extra_doble")
    private Integer horasExtraDoble;
    @MappedProperty(value = "horas_extra_triple")
    private Integer horasExtraTriple;
    @MappedProperty(value = "total_percepciones")
    private BigDecimal totalPercepciones;
    @MappedProperty(value = "total_deducciones")
    private BigDecimal totalDeducciones;
    @MappedProperty(value = "total_neto")
    private BigDecimal totalNeto;
    @MappedProperty(value = "sbc")
    private BigDecimal sbc;
    @MappedProperty(value = "provision_vacaciones")
    private BigDecimal provisionVacaciones;
    @MappedProperty(value = "provision_isn")
    private BigDecimal provisionIsn;

    @MappedProperty(value = "provision_prima_vacacional")
    private BigDecimal provisionPrimaVacacional;
    @MappedProperty(value = "provision_aguinaldo")
    private BigDecimal provisionAguinaldo;
    @MappedProperty(value = "provision_imss_patronal")
    private BigDecimal provisionImssPatronal;

    @MappedProperty(value = "liq_fin_dias_vacaciones_proporcion")
    private Integer liqFinDiasVacacionesProporcion;
    @MappedProperty(value = "liq_fin_dias_prima_vacacional_proporcion")
    private Integer liqFinDiasPrimaVacacionalProporcion;
    @MappedProperty(value = "liq_fin_dias_vacaciones_no_disfrutadas")
    private Integer liqFinDiasVacacionesNoDisfrutadas;

    @MappedProperty(value = "total_exento")
    private BigDecimal totalExento;
    @MappedProperty(value = "total_gravable")
    private BigDecimal totalGravable;
    @MappedProperty(value = "salario_diario")
    private BigDecimal salarioDiario;
    @MappedProperty(value = "tarifa_periodica_isr_id")
    private Integer tarifaAplicableSubsidioId;
    @MappedProperty(value = "tasa_aplicable_isn_id")
    private Integer tasaAplicableIsnId;
 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_antiguedad")
    private Date fechaAntiguedad;
    @MappedProperty(value = "dias_aguinaldo")
    private BigDecimal diasAguinaldo;
    @MappedProperty(value = "estado_pago_id")
    private Integer estadoPagoId;
    @MappedProperty(value = "estado_timbre_id")
    private Integer estadoTimbreId;

    @MappedProperty(value = "nomina_xperiodo_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "nomina_xperiodo_id")
    private NcrNominaXperiodo nominaXperiodoId;
    @MappedProperty(value = "persona_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    private NcoPersona personaId;
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;

    public String toQueryString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder builder = new StringBuilder("CAST(ROW(");
        builder.append(personaId.getPersonaId()).append(",");
        if(fechaContrato!=null){
            builder.append("'").append(df.format(fechaContrato)).append("',");
        }
        else {
            builder.append("null,");
        }
        builder.append(nominaXperiodoId.getNominaXperiodoId()).append(",");
        builder.append(diasLaborados).append(",");
        builder.append(diasPeriodo).append(",");
        builder.append(diasAusencia).append(",");
        builder.append(diasIncapacidad).append(",");
        builder.append(diasVacaciones).append(",");
        builder.append(diasEconomicos).append(",");
        builder.append(diasFestivoLabor).append(",");
        builder.append(diasDescansoLabor).append(",");
        builder.append(diasDescansoLaborDom).append(",");
        builder.append(diasImss).append(",");
        builder.append(horasExtraDoble).append(",");
        builder.append(horasExtraTriple).append(",");
        builder.append(totalPercepciones).append(",");
        builder.append(totalDeducciones).append(",");
        builder.append(totalNeto).append(",");
        builder.append("null,");
        builder.append("null,");
        builder.append(centrocClienteId.getCentrocClienteId()).append(",");
        builder.append(sbc).append(",");
        builder.append(provisionVacaciones).append(",");
        builder.append(provisionPrimaVacacional).append(",");
        builder.append(provisionAguinaldo).append(",");
        builder.append(provisionIsn).append(",");
        builder.append(provisionImssPatronal).append(",");
        builder.append("null,");
        builder.append(tasaAplicableIsnId).append(",");
        builder.append(liqFinDiasVacacionesProporcion).append(",");
        builder.append(liqFinDiasPrimaVacacionalProporcion).append(",");
        builder.append(totalExento).append(",");
        builder.append(totalGravable).append(",");
        builder.append(salarioDiario).append(",");
        builder.append(liqFinDiasVacacionesNoDisfrutadas).append(",");
        builder.append(diasAguinaldo).append(",");
        builder.append("null,");
        builder.append("null,");
        builder.append("null,");
        builder.append("null,");
        builder.append(estadoPagoId).append(",");
        builder.append(estadoTimbreId).append(",");
        if (fechaAntiguedad != null) {
            builder.append("'").append(df.format(fechaAntiguedad)).append("',");
        }
        else {
            builder.append("null,");
        }
        builder.append("null");
        builder.append(") AS ncr_empleado_xnomina_type)");
        return builder.toString();
    }

}
