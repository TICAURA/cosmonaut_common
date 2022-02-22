package mx.com.ga.cosmonaut.common.entity.colaborador.servicios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@MappedEntity(value = "nsc_carga_incidencias")
public class NscCargaIncidencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "carga_incidencias_id")
    private Integer cargaIncidenciasId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_carga")
    private Date fechaCarga;
    @MappedProperty(value = "archivo")
    private String archivo;
    private Integer quien;
    @MappedProperty(value = "contador_registros")
    private Integer contadorRegistros;
    @MappedProperty(value = "contador_registros_rechazados")
    private Integer contadorRegistrosRechazados;
   /** @Transient
    @MappedProperty(value = "log_errores")
    private Object logErrores;
    */
    @Relation(value = Relation.Kind.ONE_TO_MANY)
    private List<NscIncidencia> nscIncidenciaList;
    
}
