package mx.com.ga.cosmonaut.common.dto.consultas;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Introspected
@Data
public class KardexConsulta {

    private Integer kardexId;
    private Integer movimientoId;
    private String movimiento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaMovimiento;
    private Integer registroId;
    private String registroDescripcion;
    private Integer politicaId;
    private String politicaDescripcion;
    private BigDecimal salarioDiario;
    private BigDecimal salarioDiarioIntegrado;
    private BigDecimal salarioBaseCotizacion;
    private boolean esActivo;

}
