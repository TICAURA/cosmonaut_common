package mx.com.ga.cosmonaut.common.dto.imss.tectel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AfiliaRecepcionResponse {

    private String archivo;
    private String estado;
    private String exito;
    @JsonProperty("id_operacion")
    private String idOperacion;
    private String mensaje;
    private String idProceso;

}
