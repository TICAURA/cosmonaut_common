package mx.com.ga.cosmonaut.common.dto.administracion.version;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Introspected
public class AsignarQuitarRequest {

    @NotNull
    private Integer versionId;

    @NotNull
    private Integer centrocClienteId;

}
