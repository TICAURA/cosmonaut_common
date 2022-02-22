package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@MappedEntity("cat_prima_media")
public class CatPrimaMedia {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty("prima_media_id")
    private Long primaMediaId;

    @MappedProperty("descripcion")
    private String descripcion;

    @MappedProperty("tipo_riesgo")
    private String tipoRiesgo;

    @MappedProperty("en_porcentaje")
    private BigDecimal enPorcentaje;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty("fec_inicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty("fec_fin")
    private Date fechaFin;

    @MappedProperty("es_activo")
    private boolean esActivo;



}
