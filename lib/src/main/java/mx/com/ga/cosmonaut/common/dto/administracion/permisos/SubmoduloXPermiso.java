package mx.com.ga.cosmonaut.common.dto.administracion.permisos;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Introspected
public class SubmoduloXPermiso {

    @NotNull
    @Min(0)
    private Integer submoduloId;

    @NotNull
    @Min(0)
    private Integer permisoId;

}
