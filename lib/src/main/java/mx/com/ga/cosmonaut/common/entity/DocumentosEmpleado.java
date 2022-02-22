package mx.com.ga.cosmonaut.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "documentos_empleado")
public class DocumentosEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "documentos_empleado_id")
    private Integer documentosEmpleadoId;
    @MappedProperty(value = "persona_id")
    private Integer personaId;
    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;
    @MappedProperty(value = "usuario_id")
    private Integer usuarioId;
    @MappedProperty(value = "tipo_documento_id")
    private Integer tipoDocumentoId;
    @MappedProperty(value = "cms_expediente_id")
    private String cmsExpedienteId;
    @MappedProperty(value = "cms_tipo_multimedia")
    private Integer cmsTipoMultimedia;
    @MappedProperty(value = "cms_archivo_id")
    private Integer cmsArchivoId;
    @MappedProperty(value = "nombre_archivo")
    private String nombreArchivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_carga")
    private Date fechaCarga;

}
