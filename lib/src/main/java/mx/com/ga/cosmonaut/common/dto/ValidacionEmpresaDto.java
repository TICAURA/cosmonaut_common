package mx.com.ga.cosmonaut.common.dto;

import lombok.Data;

@Data
public class ValidacionEmpresaDto {

    private String mensaje;

    private boolean respuesta;
    private boolean empresa;
    private boolean domicilio;
    private boolean banco;
    private boolean imss;

}
