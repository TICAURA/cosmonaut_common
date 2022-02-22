package mx.com.ga.cosmonaut.common.dto;

import io.micronaut.core.annotation.Introspected;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Introspected
public class TablasValoresDto {

    private String tabla;
    private BigDecimal limiteInferior;
    private BigDecimal limiteSuperior;
    private BigDecimal cuotaFija;
    private BigDecimal excedente;

}
