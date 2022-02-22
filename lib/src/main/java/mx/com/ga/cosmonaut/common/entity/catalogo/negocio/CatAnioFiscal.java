package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cat_anio_fiscal")
public class CatAnioFiscal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id    
    @MappedProperty(value = "anio_ley")
    private Long anioLey;
    @MappedProperty(value = "dof")
    private String dof;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_publica_dof")
    private Date fechaPublicaDof;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    
    
}
