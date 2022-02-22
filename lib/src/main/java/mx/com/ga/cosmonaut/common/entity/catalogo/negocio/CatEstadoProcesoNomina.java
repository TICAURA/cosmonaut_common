package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@MappedEntity(value = "cat_estado_proceso_nomina")
public class CatEstadoProcesoNomina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "estado_proceso_nomina_id")
    private Integer estadoProcesoNominaId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @MappedProperty(value = "fecha_alta")
    private LocalDate fechaAlta;

}
