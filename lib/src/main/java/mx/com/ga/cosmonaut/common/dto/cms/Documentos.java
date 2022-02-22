package mx.com.ga.cosmonaut.common.dto.cms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Documentos {

    private String nombre;
    private String comentario;

    private String contenido;

    @JsonProperty(value = "tipoDocto")
    private TipoDocumento tipoDocto;
    @JsonProperty(value = "tipoMM")
    private TipoMultimedia tipoMM;


}
