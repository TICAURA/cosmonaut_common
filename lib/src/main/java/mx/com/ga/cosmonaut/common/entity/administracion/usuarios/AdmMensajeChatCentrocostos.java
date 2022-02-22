package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoMensaje;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;


import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "adm_mensaje_chat_xcentrocostos")
public class AdmMensajeChatCentrocostos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "mensaje_chat_xcentrocostos_id")
    private Integer mensajeChatCentrocostosId;
    @MappedProperty(value = "mensaje_generico")
    private String mensajeGenerico;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_mensaje_generico")
    private Date fechaMensajeGenerico;
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "usuario_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "usuario_id")
    private AdmUsuarios usuarioId;
    @MappedProperty(value = "tipo_mensaje_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_mensaje_id")
    private CatTipoMensaje tipoMensajeId;

}
