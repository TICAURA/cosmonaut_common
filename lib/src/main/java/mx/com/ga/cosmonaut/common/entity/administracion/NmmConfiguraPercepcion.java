package mx.com.ga.cosmonaut.common.entity.administracion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoBaseCalculo;
import mx.com.ga.cosmonaut.common.entity.cliente.NclPolitica;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoContratoColaborador;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoPercepcion;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@MappedEntity(value = "nmm_configura_percepcion")
public class NmmConfiguraPercepcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "configura_percepcion_id")
    private Integer configuraPercepcionId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="tipo_percepcion_id")
    @MappedProperty(value = "tipo_percepcion_id")
    private CsTipoPercepcion tipoPercepcionId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "concepto_percepcion_id")
    @MappedProperty(value = "concepto_percepcion_id")
    private NmmConceptoPercepcion conceptoPercepcionId;
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
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "valor")
    private BigDecimal valor;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @Relation(value = Relation.Kind.MANY_TO_ONE,mappedBy = "base_calculo_id")
    @MappedProperty(value = "base_calculo_id")
    private CatTipoBaseCalculo baseCalculoId;
    @MappedProperty(value = "pp_referencia")
    private String referencia;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "pp_fecha_inicio")
    private Date fechaInicio;
    @MappedProperty(value = "pp_monto_total")
    private BigDecimal montoTotal;
    @MappedProperty(value = "pp_numero_periodos")
    private Integer numeroPeriodos;
    @MappedProperty(value = "tope_integracion_sdi")
    private Boolean topeIntegracionSdi;
    @MappedProperty(value = "pp_monto_xperiodo")
    private BigDecimal montoPorPeriodo;
    @MappedProperty(value = "especializacion")
    private String especializacion;

        
}
