package mx.com.ga.cosmonaut.common.dto.imss;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GuardarCsdImssRequestDto {

    @JsonProperty(value = "csd_cer")
    private String certificadoCds;
    @JsonProperty(value = "csd_key")
    private String llaveCds;
    @JsonProperty(value = "csd_pass")
    private String contraseniaCsd;

}
