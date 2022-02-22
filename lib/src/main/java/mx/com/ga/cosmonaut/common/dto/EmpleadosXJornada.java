package mx.com.ga.cosmonaut.common.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class EmpleadosXJornada {

    private Integer jornadaId;    
    private String apellidop;
    private String apellidom;
    private String nombre;
    private String numeroemp;
    private String puesto;
}
