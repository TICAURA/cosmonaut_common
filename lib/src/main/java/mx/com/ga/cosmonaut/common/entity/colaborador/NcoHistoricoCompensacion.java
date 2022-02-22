package mx.com.ga.cosmonaut.common.entity.colaborador;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoCompensacion;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoJornada;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.cliente.NclGrupoNomina;
import mx.com.ga.cosmonaut.common.entity.cliente.NclPolitica;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedEntity(value = "nco_historico_compensacion")
public class NcoHistoricoCompensacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "historico_compensacion_id")
    private Integer historicoCompensacionId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;

    @MappedProperty(value = "salario_neto_mensual")
    private BigDecimal salarioNetoMensual;
    @MappedProperty(value = "salario_bruto_mensual")
    private BigDecimal salarioBrutoMensual;
    @MappedProperty(value = "salario_bruto_imss")
    private BigDecimal salarioBrutoImss;
    @MappedProperty(value = "salario_diario")
    private BigDecimal salarioDiario;
    @MappedProperty(value = "salario_diario_integrado")
    private BigDecimal salarioDiarioIntegrado;
    @MappedProperty(value = "salario_base_cotizacion")
    private BigDecimal salarioBaseCotizacion;
    @MappedProperty(value = "pago_complementario")
    private BigDecimal pagoComplementario;
    @MappedProperty(value = "estatus_empleado")
    private boolean estatusEmpleado;
    @MappedProperty(value = "num_empleado")
    private String numeroEmpleado;
    @DateCreated
    @MappedProperty(value = "fecha_movimiento")
    private Timestamp fechaMovimiento;
    @MappedProperty(value = "persona_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    private NcoPersona personaId;
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "grupo_nomina_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "grupo_nomina_id")
    private NclGrupoNomina grupoNominaId;
    @MappedProperty(value = "politica_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "politica_id")
    private NclPolitica politicaId;
    @MappedProperty(value = "tipo_compensacion_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_compensacion_id")
    private CatTipoCompensacion tipoCompensacionId;
    @MappedProperty(value = "tipo_jornada_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_jornada_id")
    private CsTipoJornada tipoJornadaId;


}
