package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Introspected
@Data
public class DispersionResumenConsulta {

    private Double totalNeto;
    private Double totalPagosRealizados;
    private Double totalRecibosPago;

}
