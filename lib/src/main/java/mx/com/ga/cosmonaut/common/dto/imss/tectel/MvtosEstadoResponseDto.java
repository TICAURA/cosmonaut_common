package mx.com.ga.cosmonaut.common.dto.imss.tectel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MvtosEstadoResponseDto {

    private String archivo;
    private String estado;
    private String estadoArchivo;
    private String exito;
    @JsonProperty(value = "id_operacion")
    private String idOperacion;
    private String mensaje;
    private String numeroLote;
    private String respuesta;
    private String solicitud;

}
