package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cat_tipo_asentamiento")
public class CatTipoAsentamiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @MappedProperty(value = "c_tipo_asenta")
    private Short tipoAsenta;
    @MappedProperty(value = "d_tipo_asenta")
    private String dtipoAsenta;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;

}
