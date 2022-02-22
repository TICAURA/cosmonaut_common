package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_deduccion_scfg_xnomina")
public class NcrDeduccionScfgNomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "deduccion_scfg_xnomina_id")
    private Integer deduccionScfgNominaId;
    @MappedProperty(value = "persona_id")
    private Integer personaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato_nogrupo")
    private Date fechaContratoNogrupo;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Integer nominaPeriodoId;
    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;
    @MappedProperty(value = "monto_cuota")
    private Double montoCuota;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "orden")
    private Integer orden;
    @MappedProperty(value = "concepto_deduccion_id")
    private Integer conceptoDeduccionId;
    @MappedProperty(value = "tipo_deduccion_id")
    private String tipoDeduccionId;
    @MappedProperty(value = "especializacion")
    private String especializacion;

    public String toQueryString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder builder = new StringBuilder("CAST(ROW(");
        builder.append("null,");
        builder.append(personaId).append(",");
        if(fechaContratoNogrupo!=null){
            builder.append("'").append(df.format(fechaContratoNogrupo)).append("',");
        }
        else {
            builder.append("null,");
        }
        builder.append(nominaPeriodoId).append(",");
        builder.append(centrocClienteId).append(",");
        builder.append(montoCuota).append(",");
        builder.append(esActivo).append(",");
        builder.append(orden).append(",");
        builder.append(conceptoDeduccionId).append(",");
        builder.append("'").append(tipoDeduccionId).append("',");
        builder.append("'").append(especializacion).append("',");
        builder.append("null");
        builder.append(") AS ncr_deduccion_scfg_xnomina_type)");
        return builder.toString();
    }

}
