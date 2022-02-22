package mx.com.ga.cosmonaut.common.entity.administracion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoBaseCalculo;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoDeduccion;
import mx.com.ga.cosmonaut.common.entity.cliente.NclPolitica;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoContratoColaborador;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoDescuentoInfonavit;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

@Data
@MappedEntity(value = "nmm_configura_deduccion")
public class NmmConfiguraDeduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "configura_deduccion_id")
    private Integer configuraDeduccionId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_deduccion_id")
    @MappedProperty(value = "tipo_deduccion_id")
    private CsTipoDeduccion tipoDeduccionId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "concepto_deduccion_id")
    @MappedProperty(value = "concepto_deduccion_id")
    private NmmConceptoDeduccion conceptoDeduccionId;
    @MappedProperty(value = "persona_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    private NcoPersona personaId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "politica_id")
    @MappedProperty(value = "politica_id")
    private NclPolitica politicaId;
    //Se adapta porque en BD aparece como Date y estaba con relacion a colaborador
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "cliente_id")
    @MappedProperty(value = "cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "valor")
    private BigDecimal valor;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "base_calculo_id")
    @MappedProperty(value = "base_calculo_id")
    private CatTipoBaseCalculo baseCalculoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio_descuento")
    private Date fechaInicioDescto;
    @MappedProperty(value = "numero_credito_folio_referencia")
    private String numeroFolio;
    @MappedProperty(value = "monto_total")
    private BigDecimal montoTotal;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "pa_fecha_demanda")
    private Date fechaDemanda;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "pp_fecha_otorgamiento")
    private Date fechaOtorgamiento;
    @MappedProperty(value = "pp_numero_cuotas")
    private Integer numeroCuotas;
    @MappedProperty(value = "porcentaje_interes")
    private BigDecimal interesPorcentaje;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_descuento_infonavit_id")
    @MappedProperty(value = "tipo_descuento_infonavit_id")
    private CatTipoDescuentoInfonavit tipoDescuentoInfonavitId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin_descuento")
    private Date fechaFinDescuento;
    @MappedProperty(value = "vt_folio_aviso_retencion")
    private String folioAvisoRetencion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "vt_fecha_recepcion_aviso_retencion")
    private Date fechaRecepcionAvisoRetencion;
    @MappedProperty(value = "vt_folio_aviso_suspension")
    private String folioAvisoSuspension;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "vt_fecha_recepcion_aviso_suspension")
    private Date fechaRecepcionAvisoSuspension;
    @MappedProperty(value = "especializacion")
    private String especializacion;
    @MappedProperty(value = "num_plazos_mensuales")
    private Integer numPlazosMensuales;
	
}
