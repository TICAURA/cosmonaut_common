package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "cat_tipo_emision")
public class CatTipoEmision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "id_tipo_emision")
    private Integer idTipoEmision;
    @MappedProperty(value = "tipo_emision")
    private String tipoEmision;

}
