package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class ColaboradorGrupoNominaConsulta {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroEmpleado;
    private String razonSocial;

}
