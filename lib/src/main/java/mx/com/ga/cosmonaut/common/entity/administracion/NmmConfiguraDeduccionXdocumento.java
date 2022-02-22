package mx.com.ga.cosmonaut.common.entity.administracion;

import io.micronaut.data.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.ga.cosmonaut.common.entity.DocumentosEmpleado;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoBaseCalculo;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoDescuentoInfonavit;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoDeduccion;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.cliente.NclPolitica;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoContratoColaborador;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@MappedEntity(value = "nmm_configura_deduccion_xdocumento")
public class NmmConfiguraDeduccionXdocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "configura_deduccion_xdocumento_id")
    private Integer configuraDeduccionXdocumentoId;

    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "configura_deduccion_id")
    @MappedProperty(value = "configura_deduccion_id")
    private NmmConfiguraDeduccion configuraDeduccionId;

    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "documento_suspension_id")
    @MappedProperty(value = "documento_suspension_id")
    private DocumentosEmpleado documentoSuspensionId;

    @MappedProperty(value = "documento_retencion_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "documento_retencion_id")
    private DocumentosEmpleado documentoRetencionId;

    public NmmConfiguraDeduccionXdocumento(NmmConfiguraDeduccion configuraDeduccionId) {
        this.configuraDeduccionId = configuraDeduccionId;
    }

}
