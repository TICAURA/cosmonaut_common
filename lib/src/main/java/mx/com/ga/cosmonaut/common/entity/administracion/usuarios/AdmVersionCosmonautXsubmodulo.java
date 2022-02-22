package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "adm_version_cosmonautxsubmodulo")
public class AdmVersionCosmonautXsubmodulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "version_cosmonautxsubmodulo_id")
    private Integer versionCosmonautXsubmoduloId;

    @MappedProperty(value = "version_cosmonaut_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "version_cosmonaut_id")
    private AdmVersionCosmonaut versionCosmonautId;
    @MappedProperty(value = "submodulo_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "submodulo_id")
    private AdmCatSubmodulo submoduloId;

}
