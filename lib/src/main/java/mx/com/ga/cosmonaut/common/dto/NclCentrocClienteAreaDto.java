package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import java.util.Date;
import lombok.Data;

@Data
@Introspected
public class NclCentrocClienteAreaDto {
    
    private Integer centrocClienteId;
    private String curp;
    private String rfc;
    private String razonSocial;
    private String emailCorp;
    private Boolean esActivo;
    private String registroPatronal;
    private String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAlta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaConstitucion;
    
}
