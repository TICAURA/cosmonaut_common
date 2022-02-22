package mx.com.ga.cosmonaut.common.entity.administracion;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatMoneda;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsBanco;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import java.io.Serializable;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatFuncionCuenta;

@Data
@MappedEntity(value = "nma_cuenta_banco")
public class NmaCuentaBanco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "cuenta_banco_id")
    private Integer cuentaBancoId;
    @MappedProperty(value = "clabe")
    private String clabe;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    @MappedProperty(value = "centroc_cliente_id")
    private NclCentrocCliente nclCentrocCliente;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    @MappedProperty(value = "persona_id")
    private NcoPersona ncoPersona;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "banco_id")
    @MappedProperty(value = "banco_id")
    private CsBanco bancoId;
    @Relation(value = Relation.Kind.MANY_TO_ONE,cascade = Relation.Cascade.ALL, mappedBy = "moneda_id")
    @MappedProperty(value = "moneda_id")
    private CatMoneda catMoneda;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "num_cuenta")
    private String numeroCuenta;
    @MappedProperty(value = "bin")
    private Long bin;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @MappedProperty(value = "nombre")
    private String nombreCuenta;
    @MappedProperty(value = "num_informacion")
    private String numInformacion;
    @MappedProperty(value = "num_sucursal")
    private String numSucursal;
    @MappedProperty(value = "em_usa_stp")
    private Boolean usaStp;
    @MappedProperty(value = "em_cuenta_stp")
    private String cuentaStp;
    @MappedProperty(value = "em_cuenta_clabe_stp")
    private String clabeStp;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "funcion_cuenta_id")
    @MappedProperty(value = "funcion_cuenta_id")
    private CatFuncionCuenta funcionCuentaId;
}
