package mx.com.ga.cosmonaut.common.entity.administracion;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrDeduccionXnomina;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatAnioFiscal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@MappedEntity(value = "nmm_deduccion_xanio")
public class NmmDeduccionXanio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NmmDeduccionXanioPK nmmDeduccionXanioPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @MappedProperty(value = "monto")
    private BigDecimal monto;
    @MappedProperty(value = "porcentaje")
    private BigDecimal porcentaje;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @Relation(value = Relation.Kind.ONE_TO_MANY, cascade = Relation.Cascade.ALL, mappedBy = "nmmDeduccionXanio")
    private List<NcrDeduccionXnomina> ncrDeduccionXnominaList;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private CatAnioFiscal catAnioFiscal;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private NmmDeduccion nmmDeduccion;
    
}
