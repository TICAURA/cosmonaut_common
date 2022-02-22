package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity("cs_tipo_otro_pago")
public class CsTipoOtroPago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @MappedProperty("c_tipo_otro_pago")
    private String tipoOtroPago;

    @MappedProperty("descripcion")
    private String descripcion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty("fec_inicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty("fec_fin")
    private Date fechaFin;

    @MappedProperty("es_activo")
    private boolean esActivo;
}
