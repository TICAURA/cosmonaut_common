package mx.com.ga.cosmonaut.common.entity.temporal;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.util.Date;

@Data
@MappedEntity(value = "tmp_incidencias")
public class CargaMasivaIncidencias {

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "tmp_incidencias_id")
    private Integer tmpIncidenciasId;
    @MappedProperty(value = "num_empleado")
    private String numeroEmpleado;
    @MappedProperty(value = "tipo_evento_id")
    private Integer tipoEventoId;
    @MappedProperty(value = "numero_dias")
    private Long numeroDias;
    @MappedProperty(value = "monto")
    private Double monto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_aplicacion")
    private Date fechaAplicacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @MappedProperty(value = "tipo_incapacidad_id")
    private Integer tipoIncapacidadId;
    @MappedProperty(value = "es_correcto")
    private boolean esCorrecto;
    @MappedProperty(value = "errores")
    private String errores;
    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;
    @MappedProperty(value = "unidad_medida_id")
    private Integer unidadMedidaId;
    @MappedProperty(value = "numero_horas_extras")
    private Integer numeroHorasExtras;

}
