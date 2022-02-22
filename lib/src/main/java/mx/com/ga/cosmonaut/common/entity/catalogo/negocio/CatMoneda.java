package mx.com.ga.cosmonaut.common.entity.catalogo.negocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclGrupoNomina;
import mx.com.ga.cosmonaut.common.entity.administracion.NmaCuentaBanco;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@MappedEntity(value = "cat_moneda")
public class CatMoneda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "moneda_id")
    private Long monedaId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value = "codigo_moneda")
    private String codigoMoneda;
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "monedaId")
    private List<NclGrupoNomina> nclGrupoNominaList;
    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "monedaId")
    private List<NmaCuentaBanco> nmaCuentaBancoList;
    
}
