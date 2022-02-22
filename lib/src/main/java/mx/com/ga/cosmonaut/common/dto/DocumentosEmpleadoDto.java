package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DocumentosEmpleadoDto {

    private Integer documentosEmpleadoId;
    private Integer personaId;
    private Integer centrocClienteId;
    private Integer usuarioId;
    private Integer tipoDocumentoId;
    private String cmsExpedienteId;
    private Integer cmsTipoMultimedia;
    private Integer cmsArchivoId;
    private String nombreArchivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaCarga;
    private Object tipoDocumento;
    private byte[] documento;

}
