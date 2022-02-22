package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "adm_lista_catalogos")
public class AdmListaCatalogos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "lista_catalogos_id")
    private Integer listaCatalogosId;

    @MappedProperty(value = "nombre_catalogo")
    private String nombreCatalogo;
    @MappedProperty(value = "path_servicio")
    private String pathServicio;

    @MappedProperty(value = "negocio")
    private boolean negocio;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;

}
