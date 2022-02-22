package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cs_tipo_regimen_contratacion")
public class CsTipoRegimenContratacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "tipo_regimen_contratacion_id")
    private String tipoRegimenContratacionId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;
    

}
