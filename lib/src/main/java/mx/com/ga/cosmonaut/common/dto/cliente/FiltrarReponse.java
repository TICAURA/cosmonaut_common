package mx.com.ga.cosmonaut.common.dto.cliente;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class FiltrarReponse {

    private Long centrocClienteXproveedorId;
    private Integer clienteId;
    private String cliente;
    private Integer empresaId;
    private String empresa;
    private String rfc;

}
