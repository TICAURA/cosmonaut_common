package mx.com.ga.cosmonaut.common.dto.administracion.usuarios;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@Introspected
public class GuardarRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellidoPat;

    private String apellidoMat;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Size(min = 1)
    private Set<Integer> centrocClienteIds;

    @NotNull
    private Integer rolId;

    @NotNull
    private boolean esMulticliente;

    @NotBlank
    private String version;

}
