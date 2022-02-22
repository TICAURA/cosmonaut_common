package mx.com.ga.cosmonaut.common.dto.consultas;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.sql.Timestamp;

@Introspected
@Data
public class BitacoraCosmonautConsulta {

    private Integer bitacoraCosmonautId;
    private Integer centroClienteId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rol;
    private String movimiento;
    private String modulo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    private Timestamp fechaMovimiento;

}
