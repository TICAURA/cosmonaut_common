package mx.com.ga.cosmonaut.common.dto.cms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RespuestaGestionContenido {

    @JsonProperty(value = "responseStatus")
    private RespuestaEstatus responseStatus;
    @JsonProperty(value = "data")
    private Object data;
}
