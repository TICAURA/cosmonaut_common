package mx.com.ga.cosmonaut.common.dto.confronta;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Introspected
public class GuardarRequest {

    @NotBlank
    private String registroPadronal;

    @NotBlank
    private String razonSocial;

    @NotNull
    @Min(1800)
    @Max(9999)
    private Integer anio;

    @NotNull
    @Min(1)
    @Max(12)
    private Integer mes;

    @NotNull
    @Min(0)
    private Integer emision;

}
