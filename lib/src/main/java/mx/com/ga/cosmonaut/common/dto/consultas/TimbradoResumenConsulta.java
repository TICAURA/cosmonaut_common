package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Introspected
@Data
public class TimbradoResumenConsulta {

    private Double totalNeto;
    private Double totalTimbrados;
    private Double totalRecibosTimbrados;

}
