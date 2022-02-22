package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclRepresentanteLegal;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@MappedEntity(value = "cat_tipo_representante")
public class CatTipoRepresentante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "tipo_representante_id")
    private Short tipoRepresentanteId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "tipoRepresentanteId")
    private List<NcoPersona> ncoPersonaList;
    @Relation(value = Relation.Kind.ONE_TO_MANY, cascade = Relation.Cascade.ALL,mappedBy = "tipoRepresentanteId")
    private List<NclRepresentanteLegal> nclRepresentanteLegalList;
    
}
