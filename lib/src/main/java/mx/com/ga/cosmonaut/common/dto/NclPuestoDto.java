package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import java.util.Date;
import lombok.Data;

@Data
@Introspected
public class NclPuestoDto {

    private Integer puestoId;
    private String descripcion;
    private String nombreCorto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAlta;
    private boolean esActivo;
    private Integer puestoIdReporta;
    private Integer centrocClienteId;
    private String razonSocial;  
    
}
