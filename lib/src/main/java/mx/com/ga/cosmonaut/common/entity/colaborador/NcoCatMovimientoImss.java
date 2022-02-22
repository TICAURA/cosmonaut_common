package mx.com.ga.cosmonaut.common.entity.colaborador;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "nco_cat_movimiento_imss")
public class NcoCatMovimientoImss implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "movimiento_imss_id")
    private Integer movimientoImssId;
    @MappedProperty(value = "descripcion_movimiento")
    private String descripcionMovimiento;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "clave")
    private String clave;

}
