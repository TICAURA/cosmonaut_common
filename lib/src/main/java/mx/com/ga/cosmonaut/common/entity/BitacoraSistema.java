package mx.com.ga.cosmonaut.common.entity;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedEntity(value = "bitacora_sistema")
public class BitacoraSistema implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "bitacora_sistema_id")
    private Integer bitacoraCosmonautId;
    @MappedProperty(value = "usuario_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "usuario_id")
    private AdmUsuarios usuarioId;
    @MappedProperty(value = "accion")
    private String accion;
    @DateCreated
    @MappedProperty(value = "fecha")
    private Timestamp fecha;
    @MappedProperty(value = "modulo")
    private String modulo;
    @MappedProperty(value = "centro_cliente_id")
    private Integer centroClienteId;
    @MappedProperty(value = "request")
    private String request;

}
