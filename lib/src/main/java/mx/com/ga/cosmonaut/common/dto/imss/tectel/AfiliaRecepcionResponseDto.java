package mx.com.ga.cosmonaut.common.dto.imss.tectel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AfiliaRecepcionResponseDto {

    private String archivo;
    private String estado;
    private String exito;
    @JsonProperty(value = "id_operacion")
    private String idOperacion;
    private String mensaje;
    private String respuesta;

}
