package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@MappedEntity(value = "adm_cat_permiso")
public class AdmCatPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "permiso_id")
    private Integer permisoId;

    @MappedProperty(value = "descripcion")
    private String descripcion;

    @MappedProperty(value = "es_activo")
    private boolean esActivo;

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "permisoId")
    private Set<AdmPermisosXsubmodulo> permisosXsubmodulos;

}
