package mx.com.ga.cosmonaut.common.dto.administracion.permisos;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Introspected
public class AgregarQuitarRequest {

    @NotNull
    private Integer rolId;

    @NotNull
    @Size(min = 1)
    private Set<SubmoduloXPermiso> submodulosXpemisos;

}
