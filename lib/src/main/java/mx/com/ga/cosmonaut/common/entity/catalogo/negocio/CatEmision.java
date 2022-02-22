package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "cat_emision")
public class CatEmision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "id_emision")
    private Integer idEmision;

    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "id_tipo_emision")
    @MappedProperty(value = "id_tipo_emision")
    private CatTipoEmision idTipoEmision;

    @MappedProperty(value = "mes_inicio")
    private String mesInicio;

    @MappedProperty(value = "mes_fin")
    private String mesFin;

    @MappedProperty(value = "num_mes_inicio")
    private Short numMesInicio;

    @MappedProperty(value = "num_mes_fin")
    private Short numMesFin;

}
