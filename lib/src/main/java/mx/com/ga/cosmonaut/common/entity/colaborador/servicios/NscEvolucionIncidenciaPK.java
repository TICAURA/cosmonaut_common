package mx.com.ga.cosmonaut.common.entity.colaborador.servicios;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class NscEvolucionIncidenciaPK implements Serializable {

    @MappedProperty(value = "evolucion_incidencia_id")
    private int evolucionIncidenciaId;
    
    @MappedProperty(value = "incidencia_id")
    private int incidenciaId;
    
}
