package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.Relation;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.ga.cosmonaut.common.entity.administracion.NmaCuentaBanco;
import mx.com.ga.cosmonaut.common.entity.cliente.NclGrupoNomina;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@MappedEntity(value = "cat_estatus_idse")
public class CatEstatusIdse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "estatus_idse_id")
    private Long estatusIdseId;

    @MappedProperty(value = "descripcion")
    private String descripcion;

    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;

    @MappedProperty(value = "es_activo")
    private boolean esActivo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;

    public CatEstatusIdse(Long estatusIdseId) {
        this.estatusIdseId = estatusIdseId;
    }

}
