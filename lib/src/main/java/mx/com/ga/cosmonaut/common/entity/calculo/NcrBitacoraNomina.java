package mx.com.ga.cosmonaut.common.entity.calculo;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedEntity(value = "ncr_bitacora_nomina")
public class NcrBitacoraNomina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "bitacora_nomina_id")
    private Integer bitacoraNominaId;
    @MappedProperty(value = "estado_nomina_id")
    private Integer estadoNominaId;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Integer nominaPeriodoId;
    @DateCreated
    @MappedProperty(value = "fecha")
    private Timestamp fecha;
    @MappedProperty(value = "quien")
    private Integer quien;
    @MappedProperty(value = "es_actual")
    private boolean esActual;

}
