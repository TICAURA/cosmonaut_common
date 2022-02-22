package mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cat_delegacion_subdelegacion")
public class CatDelegacionSubdelegacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @MappedProperty(value = "delegacion_subdelegacion_id")
    private Integer delegacionSubdelegacionId;

    @MappedProperty(value = "descripcion")
    private String descripcion;

    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;

    @MappedProperty(value = "delegacion_id")
    private Integer delegacionId;
}
