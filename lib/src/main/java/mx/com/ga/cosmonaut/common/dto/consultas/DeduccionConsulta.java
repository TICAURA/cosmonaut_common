package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Introspected
@Data
public class DeduccionConsulta {

    private String tipoDeduccion;
    private String concepto;
    private String clave;
    private String importe;

}
