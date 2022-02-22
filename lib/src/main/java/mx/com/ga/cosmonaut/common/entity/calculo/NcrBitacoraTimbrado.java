package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_bitacora_timbre")
public class NcrBitacoraTimbrado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "bitacora_timbre_id")
    private Integer bitacoraTimbreId;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Integer nominaPeriodoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;
    @MappedProperty(value = "cliente_id")
    private Integer clienteId;
    @MappedProperty(value = "persona_id")
    private Integer personaId;
    @MappedProperty(value = "operacion_id")
    private String operacionId;
    @MappedProperty(value = "estado_timbre_id")
    private Integer estadoTimbreId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @DateCreated
    @MappedProperty(value = "fecha_timbrado")
    private Date fechaTimbrado;
    @MappedProperty(value = "usuario_id")
    private Integer usuarioId;
    @MappedProperty(value = "tipo_proveedor_timbrado_id")
    private Integer tipoProveedorTimbradoId;
    @MappedProperty(value = "observacion")
    private String observacion;
    @MappedProperty(value = "es_actual")
    private boolean esActual;
    @MappedProperty(value = "es_correcto")
    private boolean esCorrecto;

}
