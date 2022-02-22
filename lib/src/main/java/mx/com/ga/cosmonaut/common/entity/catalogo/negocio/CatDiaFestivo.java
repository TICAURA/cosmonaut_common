package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cat_dia_festivo")
public class CatDiaFestivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "dia_festivo_id")
    private Date diaFestivoId;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    
}
