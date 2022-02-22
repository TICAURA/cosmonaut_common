package mx.com.ga.cosmonaut.common.entity.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "ncl_puesto_xarea")
public class NclPuestoXarea implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @MappedProperty(value = "puesto_id")
    private Integer puestoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @MappedProperty(value = "area_id")
    private Integer areaId;

    
}
