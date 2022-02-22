package mx.com.ga.cosmonaut.common.dto.imss.tectel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MvtosReporteDetalladoResponseDto {

    private String archivoImssRespuestaDetalle;
    private String archivoImssRespuestaPdf;
    private String estado;
    private String estadoArchivo;
    private String estadoArchivoImssRespuestaDetalle;
    private String estadoArchivoImssRespuestaPdf;
    private String exito;
    @JsonProperty(value = "id_operacion")
    private String idOperacion;
    private String mensaje;
    private String numeroLote;
    private String respuesta;
    private String solicitudImssRespuestaDetalle;
    private String solicitudImssRespuestaPdf;

}
