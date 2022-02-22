package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "adm_estatus_usuarios_chat")
public class AdmEstatusUsuariosChat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "estatus_usuarios_chat_id")
    private Integer estatusUsuariosChatId;
    @MappedProperty(value = "en_linea")
    private boolean enLinea;
    @MappedProperty(value = "usuario_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "usuario_id")
    private AdmUsuarios usuarioId;
    @MappedProperty(value = "usuario_rh")
    private boolean usuarioRh;

}
