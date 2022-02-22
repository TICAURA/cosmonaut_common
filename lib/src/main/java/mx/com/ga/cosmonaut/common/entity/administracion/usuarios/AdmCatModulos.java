package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@MappedEntity(value = "adm_cat_modulos")
public class AdmCatModulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "modulo_id")
    private Integer moduloId;

    @MappedProperty(value = "nombre_modulo")
    private String nombreModulo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_baja")
    private Date fechaBaja;

    @MappedProperty(value = "es_activo")
    private boolean esActivo;

    @MappedProperty(value = "secuencia")
    private Integer secuencia;

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "moduloId")
    private Set<AdmCatSubmodulo> submodulos;

}
