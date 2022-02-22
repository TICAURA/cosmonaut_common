package mx.com.ga.cosmonaut.common.dto.imss;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Introspected
public class IMSSFiltradoRequest {

    @NotNull
    @Min(0)
    private Integer clienteId;

    @Size(min = 1, max = 11)
    private String registroPatronal;

    @Size(min = 1, max = 32)
    private String nombre;

    @Size(min = 1, max = 32)
    private String nombre2;

    @Size(min = 1, max = 48)
    private String apellidoPat;

    @Size(min = 1, max = 48)
    private String apellidoMat;

    @Size(min = 1, max = 24)
    private String numeroEmpleado;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String fechaMovimiento;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String fechaMin;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String fechaMax;

    @Min(0)
    private Integer movimiento;

}
