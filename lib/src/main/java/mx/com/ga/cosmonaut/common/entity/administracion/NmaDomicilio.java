package mx.com.ga.cosmonaut.common.entity.administracion;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.cliente.NclSede;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import java.io.Serializable;

@Data
@MappedEntity(value = "nma_domicilio")
public class NmaDomicilio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "domicilio_id")
    private Integer domicilioId;
    @MappedProperty(value = "id_asenta_cpcons")
    private Integer asentamientoId;
    @MappedProperty(value = "c_estado")
    private Integer estado;
    @MappedProperty(value = "c_mnpio")
    private Integer municipio;

    @MappedProperty(value = "calle")
    private String calle;
    @MappedProperty(value = "num_exterior")
    private String numExterior;
    @MappedProperty(value = "num_interior")
    private String numInterior;
    @MappedProperty(value = "referencias")
    private String referencias;
    @MappedProperty(value = "d_codigo")
    private String codigo;

    @MappedProperty(value = "es_domicilio_fiscal")
    private boolean esDomicilioFiscal;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;

    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "sede_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "sede_id")
    private NclSede sedeId;
    @MappedProperty(value = "persona_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    private NcoPersona personaId;
    
}
