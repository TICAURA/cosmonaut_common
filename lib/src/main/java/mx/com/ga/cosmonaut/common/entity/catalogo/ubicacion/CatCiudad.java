package mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.Relation;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cat_ciudad")
public class CatCiudad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @MappedProperty(value = "c_cve_ciudad")
    private Short cveCiudad;

    @MappedProperty(value = "d_ciudad")
    private String ciudad;

    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;

    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "c_estado")
    @MappedProperty(value = "c_estado")
    private CatEstado estadoId;
}
