package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CargaMasivaIncidenciasDto {

    private Integer tmpIncidenciasId;
    private String numeroEmpleado;
    private Integer tipoEventoId;
    private Long numeroDias;
    private Double monto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAplicacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaInicio;
    private Integer tipoIncapacidadId;
    private boolean esCorrecto;
    private String errores;
    private Integer centrocClienteId;
    private String nombre;
    private String apellidoPaterno;

}
