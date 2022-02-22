package mx.com.ga.cosmonaut.common.entity.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "ncl_area")
public class NclAreaXEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "area_id")
    private Integer areaId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "nombre_corto")
    private String nombreCorto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @Relation(value = Relation.Kind.MANY_TO_ONE, cascade = Relation.Cascade.ALL, mappedBy = "centroc_cliente_id")
    @MappedProperty(value = "centroc_cliente_id")
    private NclCentrocCliente nclCentrocCliente;
    @MappedProperty(value = "razon_social")
    private String razonSocial;
   
    
}
