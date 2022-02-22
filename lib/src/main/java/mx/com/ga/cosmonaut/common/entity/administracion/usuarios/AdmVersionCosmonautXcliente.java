package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.io.Serializable;

@Data
@MappedEntity(value = "adm_version_cosmonautxcliente")
public class AdmVersionCosmonautXcliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "version_cosmonautxcliente_id")
    private Integer versionCosmonautXclienteId;

    @MappedProperty(value = "version_cosmonaut_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "version_cosmonaut_id")
    private AdmVersionCosmonaut versionCosmonautId;
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;

}
