package mx.com.ga.cosmonaut.common.dto.cliente;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@Introspected
public class GuardarRequest {

    @NotNull
    @Min(0)
    private Integer clienteId;

    @Min(0)
    private Integer dispersionId;

    @Min(0)
    private Integer timbradoId;

}
