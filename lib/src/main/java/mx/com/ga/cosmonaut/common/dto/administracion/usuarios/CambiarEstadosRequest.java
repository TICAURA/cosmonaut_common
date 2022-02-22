package mx.com.ga.cosmonaut.common.dto.administracion.usuarios;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Introspected
public class CambiarEstadosRequest {

    @NotNull
    @Size(min = 1)
    private List<Integer> ids;

    @NotNull
    private Boolean esActivo;

}
