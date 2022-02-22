package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.com.ga.cosmonaut.common.entity.administracion.NmmConfiguraPercepcion;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

@Data
@MappedEntity(value = "ncr_percepcion_xnomina")
public class NcrPercepcionXnomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "percepcion_xnomina_id")
    private Integer percepcionXnominaId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    @MappedProperty(value = "persona_id")
    private NcoPersona personaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato_nogrupo")
    private Date fechaContratoNoGrupo;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Integer nominaPorPeriodo;

    @MappedProperty(value = "monto_total")
    private BigDecimal montoTotal;
    @MappedProperty(value = "monto_gravable")
    private BigDecimal montoGravable;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "orden")
    private Integer orden;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    @MappedProperty(value = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "configura_percepcion_id")
    @MappedProperty(value = "configura_percepcion_id")
    private NmmConfiguraPercepcion nmmConfiguraPercepcion;
    @MappedProperty(value = "monto_exento")
    private BigDecimal montoExento;
    @MappedProperty(value = "monto_isn")
    private BigDecimal montoIsn;
    @MappedProperty(value = "monto_sdi")
    private BigDecimal montoSdi;
    @MappedProperty(value = "sdi_fijo")
    private BigDecimal sdiFijo;
    @MappedProperty(value = "sdi_variable")
    private BigDecimal sdiVariable;

    public String toQueryString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder builder = new StringBuilder("CAST(ROW(");
        builder.append("null,");
        builder.append(personaId.getPersonaId()).append(",");
        if(fechaContratoNoGrupo!=null){
            builder.append("'").append(df.format(fechaContratoNoGrupo)).append("',");
        }
        else {
            builder.append("null,");
        }
        builder.append(nominaPorPeriodo).append(",");
        builder.append(montoTotal).append(",");
        builder.append(montoGravable).append(",");
        builder.append(esActivo).append(",");
        builder.append(orden).append(",");
        builder.append(centrocClienteId.getCentrocClienteId()).append(",");
        builder.append(nmmConfiguraPercepcion.getConfiguraPercepcionId()).append(",");
        builder.append(montoExento).append(",");
        builder.append(montoIsn).append(",");
        builder.append(montoSdi).append(",");
        builder.append(sdiFijo).append(",");
        builder.append(sdiVariable);
        builder.append(") AS ncr_percepcion_xnomina_type)");
        return builder.toString();
    }

}
