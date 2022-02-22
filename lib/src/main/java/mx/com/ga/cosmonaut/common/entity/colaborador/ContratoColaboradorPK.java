package mx.com.ga.cosmonaut.common.entity.colaborador;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Embeddable;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class  ContratoColaboradorPK implements Serializable {


    
    @MappedProperty(value = "persona_id")
    private Integer personaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;
    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;
    
}
