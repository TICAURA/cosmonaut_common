package mx.com.ga.cosmonaut.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.Relation;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatPagosXliquidacion;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "pagos_liquidacion_xcolaborador")
public class PagosLiquidacionColaborador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;
    @MappedProperty(value = "persona_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    private NcoPersona personaId;
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "pagos_xliquidacion_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "pagos_xliquidacion_id")
    private CatPagosXliquidacion pagosLiquidacionId;
    @MappedProperty(value = "numero_dias")
    private Integer numeroDias;

}
