package mx.com.ga.cosmonaut.common.entity.administracion.noticias;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;


@Data
@MappedEntity(value = "adm_categoria_noticias")
public class AdmCategoriaNoticias {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(value=GeneratedValue.Type.IDENTITY)
    @MappedProperty(value="categoria_noticia_id")
    private Integer categoriaNoticiaId;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
}
