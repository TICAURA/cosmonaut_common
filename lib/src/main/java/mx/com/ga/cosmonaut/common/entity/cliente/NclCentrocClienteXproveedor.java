package mx.com.ga.cosmonaut.common.entity.cliente;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatProveedorDispersion;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatProveedorTimbrado;

import java.io.Serializable;

@Data
@MappedEntity(value = "ncl_centroc_cliente_xproveedor")
public class NclCentrocClienteXproveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "centroc_cliente_xproveedor_id")
    private Integer centrocClienteXproveedorId;

    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;

    @MappedProperty(value = "proveedor_dispersion_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "proveedor_dispersion_id")
    private CatProveedorDispersion proveedorDispersionId;

    @MappedProperty(value = "proveedor_timbrado_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "proveedor_timbrado_id")
    private CatProveedorTimbrado proveedorTimbradoId;

}
