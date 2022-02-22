package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import io.micronaut.data.jdbc.annotation.JoinTable;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@MappedEntity(value = "adm_usuarios")
public class AdmUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "usuario_id")
    private Integer usuarioId;

    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "apellido_pat")
    private String apellidoPat;
    @MappedProperty(value = "apellido_mat")
    private String apellidoMat;
    @MappedProperty(value = "email")
    private String email;
    @MappedProperty(value = "password")
    private String password;
    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_baja")
    private Date fechaBaja;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_ultimo_password")
    private Date fechaUltimoPassword;

    @MappedProperty(value = "password_provisional")
    private boolean passwordProvisional;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "es_multicliente")
    private boolean esMulticliente;

    @MappedProperty(value = "rol_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "rol_id")
    private AdmRoles rolId;

    @JoinTable(name = "adm_usuarioxcliente", joinColumns = {
            @MappedProperty(value = "usuario_id"),
            @MappedProperty(value = "centroc_cliente_id")
    })
    @Relation(value = Relation.Kind.MANY_TO_MANY)
    private List<NclCentrocCliente> centrocClientes;

}
