package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Introspected
@Data
public class PercepcionConsulta {

    private String tipoPercepcion;
    private String concepto;
    private String clave;
    private String importeExento;
    private String importeGravable;
    private String montoTotal;
    private String otroPago;

}
