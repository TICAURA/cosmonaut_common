package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@MappedEntity(value = "cs_localidad")
public class CsLocalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "localidad_id")
    private String localidadId;
    @MappedProperty(value = "nombre")
    private String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "localidadId")
    private List<CsCodPostal> csCodPostalList;
    
}
