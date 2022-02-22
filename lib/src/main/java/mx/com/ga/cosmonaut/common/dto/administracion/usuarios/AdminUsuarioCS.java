package mx.com.ga.cosmonaut.common.dto.administracion.usuarios;

import lombok.Data;

@Data
public class AdminUsuarioCS {
    private Integer personaId;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String email;
    private Integer clienteId;
}
