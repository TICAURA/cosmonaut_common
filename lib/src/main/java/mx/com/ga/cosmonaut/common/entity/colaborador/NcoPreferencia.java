package mx.com.ga.cosmonaut.common.entity.colaborador;

import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoPreferencia;

import java.io.Serializable;

@Data
@MappedEntity(value = "nco_preferencia")
public class NcoPreferencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "preferencia_id")
    private Integer preferenciaId;

    @MappedProperty(value = "valor")
    private String valor;

    @MappedProperty(value = "es_activo")
    private Boolean esActivo;

    @MappedProperty(value = "tipo_preferencia_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_preferencia_id")
    private CatTipoPreferencia tipoPreferenciaId;

    @MappedProperty(value = "persona_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    private NcoPersona personaId;

}
