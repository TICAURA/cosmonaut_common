package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_bitacora_pago")
public class NcrBitacoraPago {

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "bitacora_pago_id")
    private Long bitacoraPagoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;
    @MappedProperty(value = "cliente_id")
    private Long clienteId;
    @MappedProperty(value = "persona_id")
    private Long personaId;
    @MappedProperty(value = "operacion_id")
    private String operacionId;
    @MappedProperty(value = "estado_pago_id")
    private Long estadoPagoId;
    @DateCreated
    @MappedProperty(value = "fecha_dispersion")
    private Timestamp fechaDispersion;
    @MappedProperty(value = "usuario_id")
    private Long usuarioId;
    @MappedProperty(value = "tipo_proveedor_dispersion_id")
    private Long tipoProveedorDispersionId;
    @MappedProperty(value = "observacion")
    private String observacion;
    @MappedProperty(value = "es_actual")
    private boolean esActual;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Long nominaXperiodoId;
    @MappedProperty(value = "es_correcto")
    private boolean esCorrecto;
    @MappedProperty(value = "clave_rastreo")
    private String claveRastreo;
    @MappedProperty(value = "id")
    private Long idProveedor;

}
