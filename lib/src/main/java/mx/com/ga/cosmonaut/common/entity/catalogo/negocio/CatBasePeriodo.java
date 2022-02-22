package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@MappedEntity(value = "cat_base_periodo")
public class CatBasePeriodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "base_periodo_id")
    private Integer basePeriodoId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;
    @MappedProperty(value = "dias_periodo")
    private BigDecimal diasPeriodo;
    @MappedProperty(value = "dias_sdi")
    private BigDecimal diasSdi;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    
}
