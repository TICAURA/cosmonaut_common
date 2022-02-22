package mx.com.ga.cosmonaut.common.dto.imss.tectel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AfiliaEstadoResponseDto {

    private String estado;
    private String estadoImss;
    private String exito;
    @JsonProperty(value = "id_operacion")
    private String idOperacion;
    private String mensaje;
    private String mensajeImss;
    private String proceso;
    private String respuesta;
    private String solicitud;
    private String numeroLote;

}
