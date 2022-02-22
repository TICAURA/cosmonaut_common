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
@MappedEntity(value = "ncr_percepcion_scfg_xnomina")
public class NcrPercepcionScfgNomina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "percepcion_scfg_xnomina_id")
    private Integer percepcionScfgNominaId;
    @MappedProperty(value = "persona_id")
    private Integer personaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato_nogrupo")
    private Date fechaContratoNogrupo;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Integer nominaPeriodoId;
    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;
    @MappedProperty(value = "monto_total")
    private Double montoTotal;
    @MappedProperty(value = "monto_exento")
    private Double montoExento;
    @MappedProperty(value = "monto_gravable")
    private Double montoGravable;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "orden")
    private Integer orden;
    @MappedProperty(value = "concepto_percepcion_id")
    private Integer conceptoPercepcionId;
    @MappedProperty(value = "tipo_percepcion_id")
    private String tipoPercepcionId;
    @MappedProperty(value = "monto_isn")
    private Double montoIsn;
    @MappedProperty(value = "monto_sdi")
    private Double montoSdi;
    @MappedProperty(value = "subsidio_causado")
    private Double subsidioCausado;
    @MappedProperty(value = "sdi_fijo")
    private Double sdiFijo;
    @MappedProperty(value = "sdi_variable")
    private Double sdiVariable;
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
        builder.append(montoTotal).append(",");
        builder.append(montoExento).append(",");
        builder.append(montoGravable).append(",");
        builder.append(esActivo).append(",");
        builder.append(orden).append(",");
        builder.append(conceptoPercepcionId).append(",");
        builder.append("'").append(tipoPercepcionId).append("',");
        builder.append(montoIsn).append(",");
        builder.append(montoSdi).append(",");
        builder.append(subsidioCausado).append(",");
        builder.append(sdiFijo).append(",");
        builder.append(sdiVariable).append(",");
        builder.append("'").append(especializacion).append("'");
        builder.append(") AS ncr_percepcion_scfg_xnomina_type)");
        return builder.toString();
    }


}
