package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_timbre")
public class NcrTimbre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "timbre_id")
    private Integer timbreId;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Integer nominaPeriodoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato_nogrupo")
    private Date fechaContrato;
    @MappedProperty(value = "persona_id")
    private Integer personaId;
    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;
    @MappedProperty(value = "estado_timbre_id_actual")
    private Integer estadoTimbreIdActual;
    @MappedProperty(value = "cadena_original_sat")
    private String cadenaOriginalSat;
    @MappedProperty(value = "cfdi")
    private String cfdi;
    @MappedProperty(value = "fecha_timbrado")
    private String fechaTimbrado;
    @MappedProperty(value = "no_certificado_cfdi")
    private String noCertificadoCfdi;
    @MappedProperty(value = "no_certificado_sat")
    private String noCertificadoSat;
    @MappedProperty(value = "qr_code")
    private String qrCode;
    @MappedProperty(value = "sello_cfdi")
    private String selloCfdi;
    @MappedProperty(value = "sello_sat")
    private String selloSat;
    @MappedProperty(value = "uuid")
    private String uuid;
    @MappedProperty(value = "identificador_operacion")
    private String identificadorOperacion;
    @MappedProperty(value = "es_actual")
    private boolean esActual;

}
