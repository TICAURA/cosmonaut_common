package mx.com.ga.cosmonaut.common.entity.administracion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoPercepcion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@MappedEntity(value = "nmm_percepcion")
public class NmmPercepcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "percepcion_id")
    private Integer percepcionId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @MappedProperty(value = "porcentaje")
    private BigDecimal porcentaje;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    private Boolean quien;
    @MappedProperty(value = "tipo")
    private String tipo;
    @MappedProperty(value = "monto")
    private BigDecimal monto;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private CsTipoPercepcion tipoPercepcionId;
    @Relation(value = Relation.Kind.ONE_TO_MANY, cascade = Relation.Cascade.ALL, mappedBy = "nmmPercepcion")
    private List<NmmPercepcionXanio> nmmPercepcionXanioList;
    
}
