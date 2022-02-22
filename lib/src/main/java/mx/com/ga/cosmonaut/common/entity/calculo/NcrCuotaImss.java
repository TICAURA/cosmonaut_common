package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoCuotaImss;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_cuota_imss")
public class NcrCuotaImss implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "cuota_imss_id")
    private Integer cuotaImssId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;
    @MappedProperty(value = "porcentaje_patron")
    private Double porcentajePatron;
    @MappedProperty(value = "porcentaje_trabajador")
    private Double porcentajeTrabajador;
    @MappedProperty(value = "tope")
    private Double tope;
    @MappedProperty(value = "ind_aplica_incapacidad")
    private boolean indAplicaIncapacidad;
    @MappedProperty(value = "ind_aplica_falta")
    private boolean indAplicaFalta;
    @MappedProperty(value = "formula")
    private String formula;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;

    @MappedProperty(value = "tipo_cuota_imss_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_cuota_imss_id")
    private CatTipoCuotaImss tipoCuotaImssId;

}
