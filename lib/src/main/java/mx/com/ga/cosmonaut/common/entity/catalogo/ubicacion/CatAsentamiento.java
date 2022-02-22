package mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cat_asentamiento")
public class CatAsentamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "d_codigo")
    private String codigo;
    @Relation(value = Relation.Kind.MANY_TO_ONE,cascade = Relation.Cascade.ALL, mappedBy = "c_mnpio")
    @MappedProperty(value = "c_mnpio")
    private CatMunicipio catmnpio;
    @MappedProperty(value = "id_asenta_cpcons")
    private Integer asentamientoCpCons;
    @MappedProperty(value = "d_asenta")
    private String asentamiento;
    @MappedProperty(value = "d_cp")
    private String dcp;
    @MappedProperty(value = "c_oficina")
    private String oficina;
    @MappedProperty(value = "c_cp")
    private String ccp;
    @MappedProperty(value = "c_tipo_asenta")
    private Integer tipoAsentamiento;
    @MappedProperty(value = "c_zona")
    private Integer zona;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @Relation(value = Relation.Kind.MANY_TO_ONE,cascade = Relation.Cascade.ALL, mappedBy = "c_estado")
    @MappedProperty(value = "c_estado")
    private CatEstado edo;
    private String dMnpio;
    private String dEdo;

}
