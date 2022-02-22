package mx.com.ga.cosmonaut.common.entity.calculo;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class NcrEstadoNominaPK implements Serializable {
    
    @MappedProperty(value = "estado_nomina_id")
    private int estadoNominaId;
    @MappedProperty(value = "nomina_xperiodo_id")
    private int nominaXperiodoId;
    
}
