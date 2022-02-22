package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_estado_timbre")
public class NcrEstadoTimbre implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NcrEstadoTimbrePK ncrEstadoTimbrePK;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha")
    private Date fecha;
    /**
    @MappedProperty(value = "estado")
    private Character estado;
    */
    private Integer quien;
    @MappedProperty(value = "es_actual")
    private boolean esActual;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private NcrTimbre ncrTimbre;
    
}
