package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@MappedEntity(value = "cs_tarifa_periodica_isr")
public class CsTarifaPeriodicaIsr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "tarifa_periodica_isr_id")
    private Integer tarifaPeriodicaIsrId;
    @MappedProperty(value = "limite_inferior")
    private BigDecimal limiteInferior;
    @MappedProperty(value = "limite_superior")
    private BigDecimal limiteSuperior;
    @MappedProperty(value = "cuota_fija")
    private BigDecimal cuotaFija;
    @MappedProperty(value = "porc_excedente_lim_inf")
    private BigDecimal porcExcedenteLimInf;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "periodicidad_pago_id")
    @MappedProperty(value = "periodicidad_pago_id")
    private CsPeriodicidadPago periodicidadPagoId;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;

}
