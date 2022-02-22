package mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cat_municipio")
public class CatMunicipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "c_mnpio")
    private Integer cmnpio;

    @MappedProperty(value = "d_mnpio")
    private String dmnpio;

    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "c_estado")
    @MappedProperty(value = "c_estado")
    private CatEstado edo;
}
