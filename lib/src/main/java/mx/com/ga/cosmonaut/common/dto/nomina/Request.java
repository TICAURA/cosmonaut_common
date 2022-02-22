package mx.com.ga.cosmonaut.common.dto.nomina;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Request {

    @JsonProperty(value = "cliente_id")
    private Integer clienteId;
    @JsonProperty(value = "fecha_contrato")
    private String fechaContrato;
    @JsonProperty(value = "persona_id")
    private Integer personaId;
    @JsonProperty(value = "politica_id")
    private Integer politicaId;
    @JsonProperty(value = "grupo_nomina")
    private Integer grupoNominaId;
    @JsonProperty(value = "tipo_compensacion")
    private Integer tipoCompensacionId;
    @JsonProperty(value = "sbm_imss")
    private BigDecimal salarioBaseMensual;
    @JsonProperty(value = "fec_ini_periodo")
    private String fechaInicioPeriodo;

}
