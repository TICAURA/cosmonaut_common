package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cat_periodo_aguinaldo")
public class CatPeriodoAguinaldo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "periodo_aguinaldo_id")
    private String periodoAguinaldoId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "es_extraordinario")
    private boolean esExtraordinario;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value = "mes")
    private Short mes;
      
}
