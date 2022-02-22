package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_variabilidad_xcolaborador")
public class NcrVariabilidadXcolaborador implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;

    @MappedProperty(value = "persona_id")
    private Integer personaId;

    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;

    @MappedProperty(value = "variabilidad_id")
    private Integer variabilidadId;

    @MappedProperty(value = "factor_integracion")
    private Double factorIntegracion;
    @MappedProperty(value = "sdi_fijo")
    private Double sdiFijo;
    @MappedProperty(value = "monto_variable")
    private Double montoVariable;
    @MappedProperty(value = "dias_laborados")
    private Integer diasLaborados;

    @MappedProperty(value = "sdi_variable")
    private Double sdiVariable;
    @MappedProperty(value = "diferencia")
    private Double diferencia;


    @MappedProperty(value = "se_reporta_alimss")
    private boolean seReportaImss;

    @MappedProperty(value = "sueldo_bruto_mensual")
    private Double sueldoBrutoMensual;
    @MappedProperty(value = "sbc_anterior")
    private Double sbcAnterior;
    @MappedProperty(value = "salario_diario")
    private Double salarioDiario;
    @MappedProperty(value = "sdi_actual")
    private Double sdiActual;


}
