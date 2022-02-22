package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class NcrEmpleadoXnominaPK implements Serializable {
    
    @MappedProperty(value = "persona_id")
    private Long personaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato_nogrupo")
    private Date fechaContratoNogrupo;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Long nominaXperiodoId;
    @MappedProperty(value = "centroc_cliente_id")
    private Long centrocClienteId;
    
}
