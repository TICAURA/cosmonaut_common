package mx.com.ga.cosmonaut.common.entity.administracion;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class NmaClienteXcentroCostosPK implements Serializable {
    
    @MappedProperty(value = "centro_costos_id")
    private int centroCostosId;
    
    @MappedProperty(value = "centroc_cliente_id")
    private int centrocClienteId;
    
}
