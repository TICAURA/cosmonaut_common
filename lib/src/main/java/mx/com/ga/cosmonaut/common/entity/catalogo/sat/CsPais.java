package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@MappedEntity(value = "cs_pais")
public class CsPais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "pais_id")
    private String paisId;
    @MappedProperty(value = "cod_pais")
    private String codPais;
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
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "paisId")
    private List<CsEstado> csEstadoList;
    
}
