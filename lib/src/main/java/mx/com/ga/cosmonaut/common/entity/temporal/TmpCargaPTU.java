package mx.com.ga.cosmonaut.common.entity.temporal;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.util.Date;

@Data
@MappedEntity(value = "tmp_carga_ptu")
public class TmpCargaPTU {

    @Id
    @MappedProperty(value = "numeroempleado")
    private Integer numeroEmpleado;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "apellidopaterno")
    private String apellidoPaterno;
    @MappedProperty(value = "apellidomaterno")
    private String apellidoMaterno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fechacontrato")
    private Date fechaContrato;
    @MappedProperty(value = "diastrabajados")
    private Long diasTrabajados;
    @MappedProperty(value = "ptu")
    private Integer ptu;
    @MappedProperty(value = "indprocesado")
    private Long indProcesado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fechaInsercion")
    private Date fechaInsercion;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Long nominaXperiodoId;
}
