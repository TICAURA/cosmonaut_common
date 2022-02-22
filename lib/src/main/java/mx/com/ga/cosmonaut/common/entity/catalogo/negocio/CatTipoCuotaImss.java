package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.util.Date;

@Data
@MappedEntity("cat_tipo_cuota_imss")
public class CatTipoCuotaImss {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty("tipo_cuota_imss_id")
    private Long tipoCuotaImssId;

    @MappedProperty("codigo_rama")
    private String codigoRama;

    @MappedProperty("nombre_rama")
    private String nombreRama;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty("fecha_inicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty("fecha_fin")
    private Date fechaFin;

    @MappedProperty("es_activo")
    private boolean esActivo;

    @MappedProperty("descripcion")
    private String descripcion;

    @MappedProperty("orden")
    private Long orden;
}
