package mx.com.ga.cosmonaut.common.entity.colaborador.servicios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstadoIncidencia;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "nsc_evolucion_incidencia")
public class NscEvolucionIncidencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NscEvolucionIncidenciaPK nscEvolucionIncidenciaPK;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha")
    private Date fecha;
    private Integer quien;
    @MappedProperty(value = "es_actual")
    private boolean esActual;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private CatEstadoIncidencia estadoIncidenciaId;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private NscIncidencia nscIncidencia;
    
}
