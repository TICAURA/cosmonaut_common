package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_bitacora_variabilidad")
public class NcrBitacoraVariabilidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "bitacora_variabilidad_id")
    private Integer bitacoraVariabilidadId;

    @MappedProperty(value = "variabilidad_id")
    private Integer variabilidadId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha")
    private Date fecha;

    @MappedProperty(value = "estado_variabilidad_id")
    private Short estadoVariabilidadId;

    @MappedProperty(value = "quien")
    private Integer quien;

    @MappedProperty(value = "es_actual")
    private boolean esActual;

}
