package mx.com.ga.cosmonaut.common.entity.calculo;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
@MappedEntity(value = "ncr_deduccion_imss")
public class NcrDeduccionImss implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "deduccion_imss_id")
    private Integer deduccionImssId;
    @MappedProperty(value = "monto_cuota")
    private Double montoCuota;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "orden")
    private Integer orden;
    @MappedProperty(value = "cuota_imss_id")
    private Integer cuotaImssId;
    @MappedProperty(value = "deduccion_scfg_xnomina_id")
    private Integer deduccionScfgNominaId;

    public String toQueryString() {
        StringBuilder builder = new StringBuilder("CAST(ROW(");
        builder.append("null,");
        builder.append(montoCuota).append(",");
        builder.append(esActivo).append(",");
        builder.append(orden).append(",");
        builder.append(cuotaImssId).append(",");
        builder.append(deduccionScfgNominaId);
        builder.append(") AS ncr_deduccion_imss_type)");
        return builder.toString();
    }

}
