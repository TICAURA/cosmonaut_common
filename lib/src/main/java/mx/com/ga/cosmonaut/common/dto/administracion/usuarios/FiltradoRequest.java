package mx.com.ga.cosmonaut.common.dto.administracion.usuarios;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Introspected
public class FiltradoRequest {

    @Min(0)
    private Integer idUsuario;

    @Size(min = 1, max = 50)
    private String nombre;

    @Size(min = 1, max = 50)
    private String apellidoPat;

    @Size(min = 1, max = 50)
    private String apellidoMat;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String fechaAlta;

    @NotNull
    @Size(min = 1)
    private List<Integer> idClientes;

    @Size(min = 1, max = 90)
    private String email;

    private Boolean esActivo;

}
