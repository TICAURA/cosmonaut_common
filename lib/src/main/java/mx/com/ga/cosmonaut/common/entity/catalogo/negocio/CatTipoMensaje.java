package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "cat_tipo_mensaje")
public class CatTipoMensaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "tipo_mensaje_id")
    private Integer tipoMensajeId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;

}
