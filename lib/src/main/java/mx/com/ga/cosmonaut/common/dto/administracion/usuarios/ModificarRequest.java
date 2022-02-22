package mx.com.ga.cosmonaut.common.dto.administracion.usuarios;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Introspected
public class ModificarRequest {

    @NotNull
    private Integer usuarioId;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellidoPat;

    private String apellidoMat;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Integer rolId;

    @NotNull
    private Boolean esMulticliente;

    @NotNull
    private Boolean esActivo;

    @NotNull
    @Size(min = 1)
    private Set<Integer> centrocClienteIds;


    @NotBlank
    private String version;

}
