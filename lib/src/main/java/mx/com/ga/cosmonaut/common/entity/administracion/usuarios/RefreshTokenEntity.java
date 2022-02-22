package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RefreshTokenEntity {

    @NotBlank
    private String username;

    @NotBlank
    private String refresh_token;

    @NotNull
    private Date date_created;

}
