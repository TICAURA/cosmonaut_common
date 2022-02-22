package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NclAreaDto {

    private Integer areaId;
    private String descripcion;
    private String nombreCorto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAlta;
    private Boolean esActivo;
    private Integer areaIdAdscripcion;
    private Integer centrocClienteId;
    private List<NclPuestoDto> nclPuestoDto;
}
