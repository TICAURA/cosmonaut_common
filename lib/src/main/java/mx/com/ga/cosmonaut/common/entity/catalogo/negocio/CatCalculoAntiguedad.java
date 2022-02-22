package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@MappedEntity(value = "cat_calculo_antiguedadx")
public class CatCalculoAntiguedad implements  Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "calculo_antiguedadx_id")
    private Short calculoAntiguedadxId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;
    @MappedProperty(value = "meta_funcion")
    private String funcion;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;    
}
