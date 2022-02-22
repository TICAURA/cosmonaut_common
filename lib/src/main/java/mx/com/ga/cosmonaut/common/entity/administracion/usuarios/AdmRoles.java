package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import io.micronaut.data.jdbc.annotation.JoinTable;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@MappedEntity(value = "adm_roles")
public class AdmRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "rol_id")
    private Integer rolId;

    @MappedProperty(value = "nombre_rol")
    private String nombreRol;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_baja")
    private Date fechaBaja;

    @MappedProperty(value = "es_activo")
    private boolean esActivo;

    @JoinTable(name = "adm_rol_xcentroc_cliente", joinColumns = {
            @MappedProperty(value = "rol_id"),
            @MappedProperty(value = "centroc_cliente_id")
    })
    @Relation(value = Relation.Kind.MANY_TO_MANY)
    private List<NclCentrocCliente> centrocClientes;

}
