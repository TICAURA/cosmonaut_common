package mx.com.ga.cosmonaut.common.dto.confronta;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;



@Data
@Introspected
public class FiltradoResponse {

    private Long idConfronta;
    private String registroPatronal;
    private Integer razonSocial;
    private String anio;
    private String mes;

}
