package mx.com.ga.cosmonaut.common.entity.administracion.noticias;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

@Data
@MappedEntity("adm_noticiasxcliente")
public class AdmNoticiasxCliente {
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty("noticiasxcliente_id")
    private Integer noticiasxclienteId;
    @MappedProperty("centroc_cliente_id")
    private Integer centrocClienteId;
    @MappedProperty("noticia_id")
    private Integer noticiaId;
}
