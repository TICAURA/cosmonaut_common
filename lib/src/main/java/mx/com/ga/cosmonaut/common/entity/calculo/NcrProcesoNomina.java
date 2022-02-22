package mx.com.ga.cosmonaut.common.entity.calculo;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatEstadoProcesoNomina;

import java.io.Serializable;

@Data
@MappedEntity(value = "ncr_proceso_nomina")
public class NcrProcesoNomina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "proceso_nomina_id")
    private Integer procesoNominaId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "nomina_xperiodo_id")
    @MappedProperty(value = "nomina_xperiodo_id")
    private NcrNominaXperiodo nominaXperiodoId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "estado_proceso_nomina_id")
    @MappedProperty(value = "estado_proceso_nomina_id")
    private CatEstadoProcesoNomina estadoProcesoNominaId;
    @MappedProperty(value = "observaciones")
    private String observaciones;
    @MappedProperty(value = "visto")
    private boolean visto;

}
