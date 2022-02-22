package mx.com.ga.cosmonaut.common.entity.administracion;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class NmmDeduccionXanioPK implements Serializable {
   
    @MappedProperty(value = "anio_ley")
    private int anioLey;
    
    @MappedProperty(value = "deduccion_id")
    private int deduccionId;
    
}
