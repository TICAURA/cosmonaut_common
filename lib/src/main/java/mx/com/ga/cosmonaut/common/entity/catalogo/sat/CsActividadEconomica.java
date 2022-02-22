package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cs_actividad_economica")
public class CsActividadEconomica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "c_actividad_economica")
    private Integer actividadEconomicaId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fec_inicio")
    private Date fecInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fec_fin")
    private Date fecFin;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "nivel")
    private Integer nivel;
    @MappedProperty(value = "sector_c_actividad_economica")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "sector_c_actividad_economica")
    private CsActividadEconomica sectorCActividadEconomica;

}
