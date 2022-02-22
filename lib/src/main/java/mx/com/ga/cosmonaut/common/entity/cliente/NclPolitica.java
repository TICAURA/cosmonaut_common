package mx.com.ga.cosmonaut.common.entity.cliente;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatCalculoAntiguedad;

import java.io.Serializable;

@Data
@MappedEntity(value = "ncl_politica")
public class NclPolitica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "politica_id")
    private Integer politicaId;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;
    @MappedProperty(value = "dias_economicos")
    private Integer diasEconomicos;
    @MappedProperty(value = "prima_aniversario")
    private boolean primaAniversario;
    @MappedProperty(value = "descuenta_faltas")
    private boolean descuentaFaltas;
    @MappedProperty(value = "descuenta_incapacidades")
    private boolean descuentaIncapacidades;
    @MappedProperty(value = "costo_vales_restaurante")
    private boolean costoValesRestaurante;
    @MappedProperty(value = "descuento_prop_7o_dia")
    private boolean descuentoPropDia;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="centroc_cliente_id")
    @MappedProperty(value = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value= "es_estandar")
    private boolean esEstandar;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="calculo_antiguedadx_id")
    @MappedProperty(value = "calculo_antiguedadx_id")
    private CatCalculoAntiguedad calculoAntiguedadId;

}
