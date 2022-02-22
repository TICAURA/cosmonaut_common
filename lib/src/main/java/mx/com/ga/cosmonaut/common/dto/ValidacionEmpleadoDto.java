package mx.com.ga.cosmonaut.common.dto;

import lombok.Data;

@Data
public class ValidacionEmpleadoDto {

    private String mensaje;

    private boolean respuesta;
    private boolean informacionBasica;
    private boolean domicilio;
    private boolean informacionEmpleo;
    private boolean detalleCuenta;

    private Integer porcentaje;
}
