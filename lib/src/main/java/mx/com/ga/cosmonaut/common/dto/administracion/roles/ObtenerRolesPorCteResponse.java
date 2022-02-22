package mx.com.ga.cosmonaut.common.dto.administracion.roles;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ObtenerRolesPorCteResponse {

    private Integer rolId;
    private String nombreRol;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAlta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaBaja;
    private boolean esActivo;
    private long noUsuarios;

}
