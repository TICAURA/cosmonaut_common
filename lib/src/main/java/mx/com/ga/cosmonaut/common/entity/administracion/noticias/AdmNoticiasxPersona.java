package mx.com.ga.cosmonaut.common.entity.administracion.noticias;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

@Data
@MappedEntity("adm_noticiasxpersona")
public class AdmNoticiasxPersona {
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty("noticiasxcliente_id")
    private Integer noticiasxPersonaId;
    @MappedProperty("persona_id")
    private Integer personaId;
    @MappedProperty("noticia_id")
    private Integer noticiaId;
}
