package mx.com.ga.cosmonaut.common.entity.administracion;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoPercepcion;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.io.Serializable;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoOtroPago;

@Data
@MappedEntity(value = "nmm_concepto_percepcion")
public class NmmConceptoPercepcion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "concepto_percepcion_id")
    private Integer conceptoPercepcionId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="tipo_percepcion_id")
    @MappedProperty(value = "tipo_percepcion_id")
    private CsTipoPercepcion tipoPercepcionId;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "tipo_periodicidad")
    private String tipoPeriodicidad;
    @MappedProperty(value = "cuenta_contable")
    private String cuentaContable;
    @MappedProperty(value = "tipo_concepto")
    private String tipoConcepto;
    @MappedProperty(value = "grava_isn")
    private Boolean gravaIsn;
    @MappedProperty(value = "grava_isr")
    private String gravaIsr;
    @MappedProperty(value = "integra_imss")
    private String integraImss;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="centroc_cliente_id")
    @MappedProperty(value = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="c_tipo_otro_pago")
    @MappedProperty(value = "c_tipo_otro_pago")
    private CsTipoOtroPago tipoOtroPago;
    @MappedProperty(value = "tipo_pago")
    private String tipoPago;
    @MappedProperty(value = "periodicidad_pago_id")
    private String periodicidadPagoId;
    @MappedProperty(value = "especializacion")
    private String especializacion;

    // Agregados por Khatiana
    /*@MappedProperty(value="es_prevision")
    private Boolean esPrevision;*/

}
