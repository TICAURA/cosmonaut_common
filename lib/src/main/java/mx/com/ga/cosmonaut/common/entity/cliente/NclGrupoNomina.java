package mx.com.ga.cosmonaut.common.entity.cliente;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.administracion.NmaCuentaBanco;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatBasePeriodo;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatEsquemaPago;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatMoneda;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatPeriodoAguinaldo;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsPeriodicidadPago;

import java.io.Serializable;

@Data
@MappedEntity(value = "ncl_grupo_nomina")
public class NclGrupoNomina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "grupo_nomina_id")
    private Integer grupoNominaId;

    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;
    @MappedProperty(value = "manera_calcular_subsidio")
    private String maneraCalcularSubsidio;

    @MappedProperty(value = "isr_aguinaldo_reglamento")
    private boolean esIsrAguinaldoReglamento;
    @MappedProperty(value = "es_automatica")
    private boolean esAutomatica;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "es_predeterminado")
    private boolean esPredeterminado;
    @MappedProperty(value = "ajuste_mensual_isr")
    private boolean esAjusteMensualIsr;
    @MappedProperty(value = "pago_complementario")
    private boolean pagoComplementario;
    @MappedProperty(value = "ajustar_base_gravable_faltantes")
    private boolean ajustarBaseGravableFaltantes;

    @MappedProperty(value = "base_periodo_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "base_periodo_id")
    private CatBasePeriodo basePeriodoId;
    @MappedProperty(value = "esquema_pago_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "esquema_pago_id")
    private CatEsquemaPago esquemaPagoId;
    @MappedProperty(value = "moneda_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "moneda_id")
    private CatMoneda monedaId;
    @MappedProperty(value = "periodo_aguinaldo_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "periodo_aguinaldo_id")
    private CatPeriodoAguinaldo periodoAguinaldoId;
    @MappedProperty(value = "periodicidad_pago_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "periodicidad_pago_id")
    private CsPeriodicidadPago periodicidadPagoId;
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "cuenta_banco_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "cuenta_banco_id")
    private NmaCuentaBanco cuentaBancoId;

}
