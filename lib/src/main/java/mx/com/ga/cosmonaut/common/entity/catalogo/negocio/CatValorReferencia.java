package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@MappedEntity(value = "cat_valor_referencia")
public class CatValorReferencia  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "valor_referencia_id")
    private Long valorReferenciaId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "anio_ley")
    @MappedProperty(value = "anio_ley")
    private CatAnioFiscal anioLey;
    @MappedProperty(value = "valor")
    private BigDecimal valor;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_valor_referencia_id")
    @MappedProperty(value = "tipo_valor_referencia_id")
    private CatTipoValorReferencia tipoValorReferenciaId;
    
}
