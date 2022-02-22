package mx.com.ga.cosmonaut.common.dto.administracion.roles;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Introspected
public class ModificarRequest {

    @NotNull
    private Integer rolId;

    @NotBlank
    private String nombreRol;

    private boolean esActivo;
}
