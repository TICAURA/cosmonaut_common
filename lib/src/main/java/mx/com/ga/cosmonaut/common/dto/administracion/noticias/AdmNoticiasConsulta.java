package mx.com.ga.cosmonaut.common.dto.administracion.noticias;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Introspected
@Data
public class AdmNoticiasConsulta {
    private Integer centrocClienteId;
    private String nombre;
    private String razonSocial;
}
