package mx.com.ga.cosmonaut.common.entity.administracion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatCentroCostos;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "nma_cliente_xcentro_costos")
public class NmaClienteXcentroCostos implements Serializable {


    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NmaClienteXcentroCostosPK nmaClienteXcentroCostosPK;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fch_alta")
    private Date fchAlta;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private CatCentroCostos catCentroCostos;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private NclCentrocCliente nclCentrocCliente;
    
}
