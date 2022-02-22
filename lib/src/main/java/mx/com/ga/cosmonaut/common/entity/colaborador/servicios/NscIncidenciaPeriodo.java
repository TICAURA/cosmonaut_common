package mx.com.ga.cosmonaut.common.entity.colaborador.servicios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@MappedEntity(value = "nsc_incidencia_xperiodo")
public class NscIncidenciaPeriodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "incidencia_xperiodo_id")
    private Integer incidenciaPeriodoId;
    @MappedProperty(value = "incidencia_id")
    private Integer incidenciaId;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Integer nominaPeriodoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    @DateCreated
    private Date fechaAlta;
    @MappedProperty(value = "dias_aplicados")
    private Integer diasAplicados;
    @MappedProperty(value = "dias_acumulados")
    private Integer diasAcumulados;
    @MappedProperty(value = "ind_ultimo")
    private boolean indUltimo;

    public String toQueryString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder builder = new StringBuilder("CAST(ROW(");
        builder.append(incidenciaPeriodoId).append(",");
        builder.append(incidenciaId).append(",");
        builder.append(nominaPeriodoId).append(",");
        if(fechaAlta!=null){
            builder.append("'").append(df.format(fechaAlta)).append("',");
        }
        else {
            builder.append("null,");
        }
        builder.append(diasAplicados).append(",");
        builder.append(diasAcumulados).append(",");
        builder.append(indUltimo);
        builder.append(") AS nsc_incidencia_xperiodo_type)");
        return builder.toString();
    }

}
