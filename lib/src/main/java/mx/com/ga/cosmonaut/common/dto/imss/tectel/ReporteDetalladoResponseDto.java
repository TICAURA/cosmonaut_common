package mx.com.ga.cosmonaut.common.dto.imss.tectel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReporteDetalladoResponseDto {

    private String archivoPdf;
    private String estado;
    private String estadoArchivo;
    private String estadoArchivoDetalle;
    private String exito;
    @JsonProperty(value = "id_operacion")
    private String idOperacion;
    private String mensaje;
    private String mensajeArchivoDetalle;
    private String numeroLote;
    private String respuesta;
    private String solicitudArchivoDetalle;
    private String solicitudArchivoPdf;

}
