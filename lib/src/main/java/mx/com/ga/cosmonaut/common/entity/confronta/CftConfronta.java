package mx.com.ga.cosmonaut.common.entity.confronta;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatEmision;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cft_confronta")
public class CftConfronta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "id_confronta")
    private Long idConfronta;

    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centroc_cliente_id;

    @MappedProperty(value = "anio")
    private Short anio;

    @MappedProperty(value = "id_emision")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "id_emision")
    private CatEmision idEmision;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;

    @MappedProperty(value = "referencia_doc")
    private String referenciaDoc;

    @MappedProperty(value = "esActivo")
    private Boolean esActivo;

}
