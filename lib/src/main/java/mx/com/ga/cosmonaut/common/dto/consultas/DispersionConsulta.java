package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class DispersionConsulta {

    private String conceptoPago;
    private String cuentaBeneficiario;
    private String cuentaOrdenante;
    private String nombreBeneficiario;
    private String nombreOrdenante;
    private String rfcCurpBeneficiario;
    private String rfcCurpOrdenante;
    private Integer institucionContraparte;
    private Integer institucionOperante;
    private String referenciaNumerica;
    private Integer tipoCuentaBeneficiario;
    private Integer tipoCuentaOrdenante;
    private Integer tipoPago;
    private Double monto;

}
