package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cs_tipo_deduccion")
public class CsTipoDeduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "tipo_deduccion_id")
    private String tipoDeduccionId;
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
    @MappedProperty(value="es_por_defecto")
    private Boolean porDefecto;
    @MappedProperty(value="no_editable")
    private Boolean noEditable;
    @MappedProperty(value="base_calculo_id")
    private Integer baseCalculoId;
    @MappedProperty(value="valor")
    private Double valor;
    @MappedProperty(value="especializacion")
    private String especializacion;


}
