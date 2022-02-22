package mx.com.ga.cosmonaut.common.entity.administracion;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class NmmPercepcionXanioPK implements Serializable {
    
    @MappedProperty(value = "anio_ley")
    private int anioLey;
    
    @MappedProperty(value = "percepcion_id")
    private int percepcionId;
    
}
