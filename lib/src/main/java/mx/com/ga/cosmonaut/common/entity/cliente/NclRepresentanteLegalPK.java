package mx.com.ga.cosmonaut.common.entity.cliente;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class NclRepresentanteLegalPK implements Serializable {

    
    @MappedProperty(value = "centroc_cliente_id")
    private int centrocClienteId;
    
    @MappedProperty(value = "persona_id")
    private int personaId;
    
}
