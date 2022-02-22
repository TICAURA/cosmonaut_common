package mx.com.ga.cosmonaut.common.dto.csd;

import lombok.Data;

@Data
public class ResultadoDto {

    private String identificador_entrada;
    private String identificador_operacion;
    private String mensaje_servicio;
    private String servicio;
    private String status_servicio;

}
