package mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cat_estado")
public class CatEstado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "estado_id")
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "c_estado")
    private Integer estadoId;
    @MappedProperty(value = "d_estado")
    private String estado;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value = "clave_estado")
    private String claveEstado;

}
