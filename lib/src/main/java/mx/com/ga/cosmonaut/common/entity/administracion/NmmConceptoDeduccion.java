package mx.com.ga.cosmonaut.common.entity.administracion;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoDeduccion;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import java.io.Serializable;

@Data
@MappedEntity(value = "nmm_concepto_deduccion")
public class NmmConceptoDeduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "concepto_deduccion_id")
    private Integer conceptoDeduccionId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="tipo_deduccion_id")
    @MappedProperty(value = "tipo_deduccion_id")
    private CsTipoDeduccion tipoDeduccionId;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "cuenta_contable")
    private String cuentaContable;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="centroc_cliente_id")
    @MappedProperty(value = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "especializacion")
    private String especializacion;


}
