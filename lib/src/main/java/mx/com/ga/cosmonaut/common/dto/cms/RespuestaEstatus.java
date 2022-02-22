package mx.com.ga.cosmonaut.common.dto.cms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RespuestaEstatus {

    @JsonProperty(value = "code")
    private String code;
    @JsonProperty(value = "desc")
    private String desc;
    @JsonProperty(value = "tokenOperation")
    private String tokenOperation;

}
