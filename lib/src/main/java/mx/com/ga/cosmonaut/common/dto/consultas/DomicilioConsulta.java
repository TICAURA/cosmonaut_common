package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class DomicilioConsulta {

    private Integer domicilioId;
    private String codigoPostal;
    private String entidadFederativa;
    private String municipio;
    private String colonia;
    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private Integer sedeId;
    private String sedeNombre;
}
