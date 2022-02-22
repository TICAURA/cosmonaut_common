package mx.com.ga.cosmonaut.common.entity.cliente;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatCalculoAntiguedad;

@Data
@MappedEntity(value = "ncl_politica")
public class NclPoliticaBeneficio implements Serializable {

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
    private Short diasEconomicos;
    @MappedProperty(value = "prima_aniversario")
    private Boolean primaAniversario;
    @MappedProperty(value = "descuenta_faltas")
    private Boolean descuentaFaltas;
    @MappedProperty(value = "descuenta_incapacidades")
    private Boolean descuentaIncapacidades;
    @MappedProperty(value = "costo_vales_restaurante")
    private Boolean costoValesRestaurante;
    @MappedProperty(value = "descuento_prop_7o_dia")
    private boolean descuentoPropDia;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "calculo_antiguedadx")
    private String calculoAntiguedadx;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="centroc_cliente_id")
    @MappedProperty(value = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="calculo_antiguedadx_id")
    @MappedProperty(value = "calculo_antiguedadx_id")
    private CatCalculoAntiguedad calculoAntiguedadId;
    @MappedProperty(value= "es_estandar")
    private Boolean esEstandar;
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy="politica_id")
    @MappedProperty(value = "politica_id")
    private NclBeneficioXpolitica nclBeneficioXpolitica;
    

}
