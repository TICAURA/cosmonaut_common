package mx.com.ga.cosmonaut.common.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected

public class TablasExtDto {

    private String tabla;
    private String periodo;
    private boolean esActivo;
}
