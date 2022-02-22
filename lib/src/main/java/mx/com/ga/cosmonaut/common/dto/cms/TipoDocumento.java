package mx.com.ga.cosmonaut.common.dto.cms;

import lombok.Data;

@Data
public class TipoDocumento {

    private Integer id;
    private String acronimo;
    private String descripcion;
    private String nombre;
    private boolean activo;

}
