package mx.com.ga.cosmonaut.common.dto.cliente;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Introspected
public class ModificarRequest {

    @NotNull
    @Min(0)
    private Integer clienteXproveedorId;

    @NotNull
    @Min(0)
    private Integer clienteId;

    @NotNull
    @Min(0)
    private Integer dispersionId;

    @NotNull
    @Min(0)
    private Integer timbradoId;

}
