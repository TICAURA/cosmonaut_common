package mx.com.ga.cosmonaut.common.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class TablasDto {

    private String tabla;
    private String periodo;

}
