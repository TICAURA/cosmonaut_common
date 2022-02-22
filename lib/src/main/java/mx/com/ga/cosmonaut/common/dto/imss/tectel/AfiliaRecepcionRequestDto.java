package mx.com.ga.cosmonaut.common.dto.imss.tectel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AfiliaRecepcionRequestDto {

    private String servicio;
    @JsonProperty(value = "id_csd")
    private String idCsd;
    private String registroPatronal;
    private String archivoDispmagZip;
    private String idProceso;

}
