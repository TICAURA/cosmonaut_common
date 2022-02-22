package mx.com.ga.cosmonaut.common.dto.administracion.usuarios;

import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;

import java.util.List;
import java.util.Set;

@Data
public class AdminUsuarioDto {

    private List<AdmUsuarios> usuarios;
    private Long totalRegistros;

}
