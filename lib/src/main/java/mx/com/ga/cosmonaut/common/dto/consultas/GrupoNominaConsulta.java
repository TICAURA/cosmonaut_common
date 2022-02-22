package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class GrupoNominaConsulta {

    private Integer id;
    private String nombre;
    private String razon;
    private String periodo;
    private Long numero;
    private Boolean pagoComplementario;

}
