package mx.com.ga.cosmonaut.common.entity.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@MappedEntity(value = "ncl_puesto_xarea")
@Introspected
public class PuestosXArea {

    @Id
    @Relation(value = Relation.Kind.MANY_TO_ONE, cascade = Relation.Cascade.ALL, mappedBy = "area_id")
    @MappedProperty(value = "area_id")
    private NclArea areaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @MappedProperty(value = "puesto_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, cascade = Relation.Cascade.ALL, mappedBy = "puesto_id")
    private NclPuesto puestoId;
    
}
