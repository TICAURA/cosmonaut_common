package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cs_estado")
public class CsEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "estado_id")
    private String estadoId;
    @MappedProperty(value = "cod_estado")
    private String codEstado;
    @MappedProperty(value = "nombre")
    private String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_final")
    private Date fechaFinal;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "pais_id")
    @MappedProperty(value = "pais_id")
    private CsPais paisId;
    
}
