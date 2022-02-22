package mx.com.ga.cosmonaut.common.dto.cliente;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class CredencialesImssDto {

    private String usuarioImss;
    private String pwdImss;
    private String certificadoImss;

}
