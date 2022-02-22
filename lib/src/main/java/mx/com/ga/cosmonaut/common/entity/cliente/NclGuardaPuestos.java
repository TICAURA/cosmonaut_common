package mx.com.ga.cosmonaut.common.entity.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Introspected
@MappedEntity(value = "ncl_puesto")
public class NclGuardaPuestos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "puesto_id")
    private Integer puestoId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value="puesto_id_reporta")
    private Integer puestoIdReporta;
    @MappedProperty(value= "centroc_cliente_id")
    private Integer centrocClienteId;

}
