package mx.com.ga.cosmonaut.common.dto.cliente;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Introspected
public class FiltrarRequest {

    @Min(0)
    private Integer clienteId;

    @Min(0)
    private Integer empresaId;

}
