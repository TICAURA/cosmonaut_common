package mx.com.ga.cosmonaut.common.dto.cliente;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class ListarCompaniaSimpleResponse {

    private Integer centrocClienteId;
    private String rfc;
    private String razonSocial;
    private String nombre;
    private Boolean esActivo;

}
