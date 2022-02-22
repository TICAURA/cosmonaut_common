package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.util.Date;

@Data
@MappedEntity("cat_tipo_nomina")
public class CatTipoNomina {

    private static final long serialVersionUID = 1L;

    @Id
    @MappedProperty("tipo_nomina_id")
    private Long tipoNominaId;

    @MappedProperty("descripcion")
    private String descripcion;

    @MappedProperty("nombre_corto")
    private String nombreCorto;

    @MappedProperty("es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty("fecha_alta")
    private Date fechaAlta;

    @MappedProperty("es_extraordinaria")
    private boolean esExtraordinaria;

}
