package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import io.micronaut.data.jdbc.annotation.JoinTable;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@MappedEntity(value = "adm_cat_submodulo")
public class AdmCatSubmodulo implements Serializable, Comparable<AdmCatSubmodulo> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "submodulo_id")
    private Integer submoduloId;

    @MappedProperty(value = "nombre_submodulo")
    private String nombreSubmodulo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_baja")
    private Date fechaBaja;

    @MappedProperty(value = "es_activo")
    private boolean esActivo;

    @MappedProperty(value = "modulo_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "modulo_id")
    private AdmCatModulos moduloId;

    @MappedProperty(value = "path_servicio")
    private String pathServicio;

    @MappedProperty(value = "secuencia")
    private Integer secuencia;

    @JoinTable(name = "adm_permisosXsubmodulo", joinColumns = {
            @MappedProperty(value = "submodulo_id"),
            @MappedProperty(value = "permiso_id")
    })
    @Relation(value = Relation.Kind.MANY_TO_MANY)
    private Set<AdmCatPermiso> permisos;

    @Override
    public int compareTo(@NotNull AdmCatSubmodulo o) {
        if(this.moduloId.getModuloId()-o.getModuloId().getModuloId()==0)
            return this.getSubmoduloId()-o.getSubmoduloId();
        return this.moduloId.getModuloId()-o.getModuloId().getModuloId();
    }
}
