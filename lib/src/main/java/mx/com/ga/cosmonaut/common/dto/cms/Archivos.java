package mx.com.ga.cosmonaut.common.dto.cms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Archivos {

    private Object archivo;
    private Expediente expediente;
    @JsonProperty(value = "original")
    private Origen original;

}
