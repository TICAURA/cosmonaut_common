package mx.com.ga.cosmonaut.common.entity.calculo;

import io.micronaut.data.annotation.*;

import java.io.Serializable;

@Embeddable
public class NcrEstadoTimbrePK implements Serializable {
    
    @MappedProperty(value = "estado_timbre_id")
    private int estadoTimbreId;
    @MappedProperty(value = "timbre_id")
    private int timbreId;
   
}
