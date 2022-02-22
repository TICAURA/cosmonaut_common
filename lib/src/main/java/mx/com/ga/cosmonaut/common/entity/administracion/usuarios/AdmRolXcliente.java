package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.io.Serializable;

@Data
@MappedEntity(value = "adm_rol_xcentroc_cliente")
public class AdmRolXcliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "rol_xcentroc_cliente_id")
    private Integer rolXcentrocclienteId;

    @MappedProperty(value = "rol_id")
    private AdmRoles roldId;
    @MappedProperty(value = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;

}
