package mx.com.ga.cosmonaut.common.dto;

import io.micronaut.core.annotation.Introspected;
import java.math.BigDecimal;
import lombok.Data;


@Data
@Introspected
public class NclBeneficiosDto {
    
    private Integer beneficioXPolitica;  
    private NclPoliticaDto nclPoliticaDtoId;
    private Short aniosAntiguedad;
    private Short diasAguinaldo;
    private Short diasVacaciones;
    private BigDecimal primaVacacional;
    private Boolean esActivo;
}
