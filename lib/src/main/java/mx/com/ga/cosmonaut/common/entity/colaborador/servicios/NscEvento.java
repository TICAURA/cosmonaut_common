package mx.com.ga.cosmonaut.common.entity.colaborador.servicios;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "nsc_evento")
public class NscEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    
    @MappedProperty(value = "evento_id")
    private Integer eventoId;
    
}
