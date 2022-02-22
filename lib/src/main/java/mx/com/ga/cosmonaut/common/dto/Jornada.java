package mx.com.ga.cosmonaut.common.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class Jornada {

    private Integer jornadaId;  
    private String tipoJornadaId;
    private String descripcion;
    private String nombre;
    private Integer count;
    private Integer sumaHorasJornadaId;

}
