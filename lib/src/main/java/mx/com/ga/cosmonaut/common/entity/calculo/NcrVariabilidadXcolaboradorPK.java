package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Embeddable
public class NcrVariabilidadXcolaboradorPK implements Serializable  {

    private static final long serialVersionUID = 1L;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;

    @MappedProperty(value = "persona_id")
    private Integer personaId;

    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;

    @MappedProperty(value = "variabilidad_id")
    private Integer variabilidadId;

}
