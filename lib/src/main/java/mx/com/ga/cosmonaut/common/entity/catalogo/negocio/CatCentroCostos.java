package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cat_centro_costos")
public class CatCentroCostos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "cetro_costos_id")
    private Long cetroCostosId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "nom_corto")
    private String nomCorto;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fch_alta")
    private Date fchAlta;
   

}
