package mx.com.ga.cosmonaut.common.dto.administracion.usuarios;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Introspected
public class CambiarPwdRequest {

    @NotNull
    private Integer usuarioId;

    @NotBlank
    private String oldPwd;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&*_+-=,.?]).{8,}$")
    private String newPwd;

}
