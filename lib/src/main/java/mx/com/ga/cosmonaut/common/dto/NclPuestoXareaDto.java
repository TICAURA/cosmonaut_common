package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class NclPuestoXareaDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAlta;
    private boolean esActivo;
    private Integer areaId;
    private Integer puestoId;
    private String nombreArea;
    private String nombrePuesto;
    
}
