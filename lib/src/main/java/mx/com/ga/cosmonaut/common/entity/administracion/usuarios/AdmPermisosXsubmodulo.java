package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "adm_permisosxsubmodulo")
public class AdmPermisosXsubmodulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "permisoxsubmodulo_id")
    private Integer permisoXsubmoduloId;

    @MappedProperty(value = "permiso_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "permiso_id")
    private AdmCatPermiso permisoId;
    @MappedProperty(value = "submodulo_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "submodulo_id")
    private AdmCatSubmodulo submoduloId;

}
