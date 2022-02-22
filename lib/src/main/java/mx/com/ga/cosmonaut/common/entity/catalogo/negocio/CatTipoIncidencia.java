package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoDeduccion;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoPercepcion;
import mx.com.ga.cosmonaut.common.entity.colaborador.servicios.NscIncidencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@MappedEntity(value = "cat_tipo_incidencia")
public class CatTipoIncidencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "tipo_incidencia_id")
    private Integer tipoIncidenciaId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;
    @MappedProperty(value = "es_incidencia")
    private Boolean esIncidencia;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value = "p_tipo_percepcion_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "p_tipo_percepcion_id")
    private CsTipoPercepcion tipoPercepcionId;
    @MappedProperty(value = "p_especializacion")
    private String percepcionEspecializacion;
    @MappedProperty(value = "d_tipo_deduccion_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "d_tipo_deduccion_id")
    private CsTipoDeduccion tipoDeduccionId;
    @MappedProperty(value = "d_especializacion")
    private String deduccionEspecializacion;
    
}
