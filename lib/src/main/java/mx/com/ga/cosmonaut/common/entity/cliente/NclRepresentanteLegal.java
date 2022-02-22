package mx.com.ga.cosmonaut.common.entity.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoRepresentante;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "ncl_representante_legal")
public class NclRepresentanteLegal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NclRepresentanteLegalPK nclRepresentanteLegalPK;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private CatTipoRepresentante tipoRepresentanteId;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private NclCentrocCliente nclCentrocCliente;
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private NcoPersona ncoPersona;
    
}
