package mx.com.ga.cosmonaut.common.entity.colaborador;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedEntity(value = "nco_chat_colaborador")
public class NcoChatColaborador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "chat_colaborador_id")
    private Long chatColaboradorId;
    @MappedProperty(value = "mensajes")
    private String mensajes;
    @MappedProperty(value = "conversacion_id")
    private String conversacionId;
    @MappedProperty(value = "fecha_ultimo_mensaje")
    private Timestamp fechaUltimoMensaje;
    @MappedProperty(value = "es_actual")
    private boolean esActual;
    @MappedProperty(value = "usuario_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "usuario_id")
    private AdmUsuarios usuarioId;
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value="nombre_rrh")
    private String nombreRrh;
    @MappedProperty(value="atendido")
    private boolean atendido;
    @MappedProperty(value="id_usuario_rrh")
    private Integer idUsuarioRrh;


}
