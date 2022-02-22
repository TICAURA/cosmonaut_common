package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "adm_version_submodulo_permiso")
public class AdmVersionSubmoduloPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "version_submodulo_permiso_id")
    private Integer versionSubmoduloPermisoId;

    @MappedProperty(value = "version_cosmonaut_id")
    private Integer versionCosmonautId;
    @MappedProperty(value = "submodulo_id")
    private Integer submoduloId;
    @MappedProperty(value = "permiso_id")
    private Integer permisoId;

}
