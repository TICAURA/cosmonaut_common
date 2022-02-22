package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.com.ga.cosmonaut.common.entity.administracion.NmmConfiguraDeduccion;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

@Data
@MappedEntity(value = "ncr_deduccion_xnomina")
public class NcrDeduccionXnomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "deduccion_xnomina_id")
    private Integer deduccionXnominaId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    @MappedProperty(value = "persona_id")
    private NcoPersona personaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato_nogrupo")
    private Date fechaContratoNoGrupo;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Integer nominaPorPeriodo;
    @MappedProperty(value = "monto_cuota")
    private BigDecimal montoTotal;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @MappedProperty(value = "orden")
    private Integer orden;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    @MappedProperty(value = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "configura_deduccion_id")
    @MappedProperty(value = "configura_deduccion_id")
    private NmmConfiguraDeduccion nmmConfiguraDeduccion;

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
        builder.append(esActivo).append(",");
        builder.append(orden).append(",");
        builder.append(centrocClienteId.getCentrocClienteId()).append(",");
        builder.append(nmmConfiguraDeduccion.getConfiguraDeduccionId());
        builder.append(") AS ncr_deduccion_xnomina_type)");
        return builder.toString();
    }

}
