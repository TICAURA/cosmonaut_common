package mx.com.ga.cosmonaut.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "carga_ptu")
public class CargaPTU implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "carga_ptu_id")
    private Long cargaPtuId;
    @MappedProperty(value = "numeroempleado")
    private String numeroEmpleado;
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
    private Double ptu;
    @MappedProperty(value = "indprocesado")
    private Long indProcesado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fechainsercion")
    private Date fechaInsercion;
    @MappedProperty(value = "nomina_xperiodo_id")
    private Long nominaPeriodoId;
    @MappedProperty(value = "centroc_cliente_id")
    private Long centrocClienteId;
    @MappedProperty(value = "personaid")
    private Long personaId;

}
