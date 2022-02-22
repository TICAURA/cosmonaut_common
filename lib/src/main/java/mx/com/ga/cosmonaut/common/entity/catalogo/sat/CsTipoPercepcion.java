package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@MappedEntity(value = "cs_tipo_percepcion")
public class CsTipoPercepcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "tipo_percepcion_id")
    private String tipoPercepcionId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;
    @MappedProperty(value = "tipo_concepto")
    private String tipoConcepto;
    @MappedProperty(value = "integra_sdi")
    private String integraSdi;    
    @MappedProperty(value = "tope_integra_sdi")
    private BigDecimal topeIntegraSdi;
    @MappedProperty(value = "tipo_periodicidad")
    private String tipoPeriodicidad;
    @MappedProperty(value="es_por_defecto")
    private Boolean porDefecto;
    @MappedProperty(value = "integra_isr")
    private String integraIsr;
    @MappedProperty(value = "tope_integra_isr")
    private BigDecimal topeIntegraIsr;
    @MappedProperty(value = "integra_isn")
    private String integraIsn;
    @MappedProperty(value = "tipo_valor_referencia_id")
    private Integer tipoValorReferenciaId;
    @MappedProperty(value="c_tipo_otro_pago")
    private CsTipoOtroPago tipoOtroPago;

    @MappedProperty(value="tipo_pago")
    private String tipoPago;
    @MappedProperty(value="periodicidad_pago_id_xisr")
    private String periodicidadPagoIdIsr;
    @MappedProperty(value="tipo_calculo_tope")
    private String tipoCalculoTope;
    @MappedProperty(value="no_editable")
    private boolean noEditable;
    @MappedProperty(value="base_calculo_id")
    private Integer baseCalculoId;
    @MappedProperty(value="valor")
    private Double valor;
    @MappedProperty(value="especializacion")
    private String especializacion;

    // Agregados por Khatiana
    @MappedProperty(value="aplica_prevision")
    private Boolean aplicaPrevision ;
    @MappedProperty(value="es_configurablex")
    private String esConfigurablex;

}
