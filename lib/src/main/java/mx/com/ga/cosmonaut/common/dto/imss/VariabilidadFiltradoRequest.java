package mx.com.ga.cosmonaut.common.dto.imss;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Introspected
public class VariabilidadFiltradoRequest {

    @Min(0)
    private Integer clienteId;
    private Integer anio;
    private Integer bimestre;

}
