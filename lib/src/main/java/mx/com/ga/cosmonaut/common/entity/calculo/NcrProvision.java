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
@MappedEntity(value = "ncr_provision")
public class NcrProvision implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "provision_xnomina_id")
    private Integer provisionNominaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato_nogrupo")
    private Date fechaContratoNogrupo;
    @MappedProperty(value = "persona_id")
    private Integer personaId;
    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Integer nominaPeriodoId;
    @MappedProperty(value = "anio_ley")
    private Integer anioLey;
    @MappedProperty(value = "monto_provision")
    private Double montoProvision;
    @MappedProperty(value = "orden")
    private Integer orden;
    @MappedProperty(value = "cuota_imss_id")
    private Integer cuotaImssId;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;

    public String toQueryString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder builder = new StringBuilder("CAST(ROW(");
        builder.append("null,");
        if(fechaContratoNogrupo!=null){
            builder.append("'").append(df.format(fechaContratoNogrupo)).append("',");
        }
        else {
            builder.append("null,");
        }
        builder.append(personaId).append(",");
        builder.append(centrocClienteId).append(",");
        builder.append(nominaPeriodoId).append(",");
        builder.append(anioLey).append(",");
        builder.append(montoProvision).append(",");
        builder.append(orden).append(",");
        builder.append(cuotaImssId).append(",");
        builder.append(esActivo);
        builder.append(") AS ncr_provision_type)");
        return builder.toString();
    }


}
