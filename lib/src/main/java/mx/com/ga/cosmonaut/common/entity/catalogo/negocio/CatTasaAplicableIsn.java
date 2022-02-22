package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstado;

@Data
@MappedEntity(value = "cat_tasa_aplicable_isn")
public class CatTasaAplicableIsn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "tasa_aplicable_isn_id")
    private Integer tasaAplicableIsnId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;
    @MappedProperty(value = "limite_inferior")
    private BigDecimal limiteInferior;
    @MappedProperty(value = "limite_superior")
    private BigDecimal limiteSuperior;
    @MappedProperty(value = "cuota_fija")
    private BigDecimal cuotaFija;
    @MappedProperty(value = "tasa")
    private BigDecimal tasa;
    @MappedProperty(value = "referencia_marco_juridico")
    private String referenciaMarcoJuridico;
    @MappedProperty(value = "url_marco_juridico")
    private String urlMarcoJuridico;
    @Relation(value = Relation.Kind.MANY_TO_ONE,cascade = Relation.Cascade.ALL, mappedBy = "c_estado")
    @MappedProperty(value = "c_estado")
    private CatEstado cestado;
    
}
