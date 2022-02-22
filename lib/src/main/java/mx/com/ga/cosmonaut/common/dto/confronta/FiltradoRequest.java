package mx.com.ga.cosmonaut.common.dto.confronta;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Introspected
public class FiltradoRequest {

    @NotNull
    @Min(0)
    private Integer clienteId;

    @Size(min = 1, max = 11)
    private String registroPatronal;

    @Size(min = 1, max = 32)
    private String razonSocial;

    @Size(min = 1, max = 2)
    @Pattern(regexp = "^\\d{2}$")
    private String anio;

    @Size(min = 4, max = 12)
    private String mes;

}
