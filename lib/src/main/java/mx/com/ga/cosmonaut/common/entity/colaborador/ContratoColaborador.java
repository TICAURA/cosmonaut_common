package mx.com.ga.cosmonaut.common.entity.colaborador;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.calculo.NcrEmpleadoXnominaPK;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.*;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoContrato;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoRegimenContratacion;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatAreaGeografica;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstado;
import mx.com.ga.cosmonaut.common.entity.cliente.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@MappedEntity(value = "nco_contrato_colaborador")
public class ContratoColaborador implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ContratoColaboradorPK contratoColaboradorPK;
    @MappedProperty(value = "salario_diario")
    private BigDecimal salarioDiario;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "de_fecha_antiguedad")
    private Date fechaAntiguedad;
    @MappedProperty(value = "de_es_sindicalizado")
    private boolean esSindicalizado;
    @MappedProperty(value = "su_es_subcontratado")
    private boolean esSubcontratado;
    @MappedProperty(value = "su_porcentaje")
    private BigDecimal porcentaje;
    @MappedProperty(value = "sueldo_bruto_mensual")
    private BigDecimal sueldoBrutoMensual;
    @MappedProperty(value = "num_empleado")
    private String numEmpleado;
    @MappedProperty(value = "sbc")
    private BigDecimal sbc;
    @MappedProperty(value = "dias_vacaciones")
    private Short diasVacaciones;
    @MappedProperty(value = "ba_fecha_para_calculo")
    private String fechaParaCalculo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "ba_ultimo_dia")
    private Date ultimoDia;
    @MappedProperty(value = "ba_notas")
    private String notas;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @MappedProperty(value = "sueldo_neto_mensual")
    private BigDecimal sueldoNetoMensual;
    @MappedProperty(value = "su_subcontratista")
    private String subcontratista;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta_imss")
    private Date fechaAltaImss;
    @MappedProperty(value = "salario_diario_integrado")
    private BigDecimal salarioDiarioIntegrado;
    @MappedProperty(value = "ppp_sbm")
    private BigDecimal pppSalarioBaseMensual;
    @MappedProperty(value = "ppp_monto_complementario")
    private BigDecimal pppMontoComplementario;
    @MappedProperty(value = "estatus_baja_id")
    private Integer estatusBajaId;
    @MappedProperty(value = "ppp_snm")
    private BigDecimal pppSnm;
    @MappedProperty(value = "de_tipo_jornada_id")
    private String tipoJornadaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin_ultimo_pago")
    private Date fechaFinUltimoPago;

    @MappedProperty(value = "sede_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "sede_id")
    private NclSede sedeId;
    @MappedProperty(value = "puesto_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "puesto_id")
    private NclPuesto puestoId;
    @MappedProperty(value = "area_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "area_id")
    private NclArea areaId;
    @MappedProperty(value = "de_tipo_contrato_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "de_tipo_contrato_id")
    private CsTipoContrato tipoContratoId;
    @MappedProperty(value = "de_tipo_regimen_contratacion_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "de_tipo_regimen_contratacion_id")
    private CsTipoRegimenContratacion tipoRegimenContratacionId;
    @MappedProperty(value = "de_area_geografica_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "de_area_geografica_id")
    private CatAreaGeografica areaGeograficaId;
    @MappedProperty(value = "su_subcontratista_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "su_subcontratista_id")
    private CatSubcontratista subcontratistaId;
    @MappedProperty(value = "grupo_nomina_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "grupo_nomina_id")
    private NclGrupoNomina grupoNominaId;
    @MappedProperty(value = "politica_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "politica_id")
    private NclPolitica politicaId;
    @MappedProperty(value = "tipo_compensacion_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_compensacion_id")
    private CatTipoCompensacion tipoCompensacionId;
    @MappedProperty(value = "de_jornada_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "de_jornada_id")
    private NclJornada jornadaId;
    @MappedProperty(value = "metodo_pago_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "metodo_pago_id")
    private CatMetodoPago metodoPagoId;
    @MappedProperty(value = "ba_tipo_baja_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "ba_tipo_baja_id")
    private CatTipoBaja tipoBajaId;
    @MappedProperty(value = "ba_motivo_baja_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "ba_motivo_baja_id")
    private CatMotivoBaja motivoBajaId;
    @MappedProperty(value = "de_c_estado")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "de_c_estado")
    private CatEstado estadoId;
    @MappedProperty(value = "jefe_inmediato_persona_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "jefe_inmediato_persona_id")
    private NcoPersona jefeInmediatoId;



}
