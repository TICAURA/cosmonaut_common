package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "adm_permisosxsubmoduloxrol")
public class AdmPermisosXsubmoduloXrol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "permisosxsubmoduloxrol_id")
    private Integer permisosXsubmoduloXrolId;

    @MappedProperty(value = "rol_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "rol_id")
    private AdmRoles rolId;
    @MappedProperty(value = "permisoxsubmodulo_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "permisoxsubmodulo_id")
    private AdmPermisosXsubmodulo permisoXsubmoduloId;

}
