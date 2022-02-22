package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity("cat_tipo_valor_referencia")
public class CatTipoValorReferencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty("tipo_valor_referencia_id")
    private Long tipoValorReferenciaId;

    @MappedProperty("descripcion")
    private String descripcion;

    @MappedProperty("nombre_corto")
    private String nombreCorto;

    @MappedProperty("es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @DateCreated
    @MappedProperty("fecha_alta")
    private Date fechaAlta;
}
