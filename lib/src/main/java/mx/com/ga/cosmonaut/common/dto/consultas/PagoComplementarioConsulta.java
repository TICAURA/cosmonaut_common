package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class PagoComplementarioConsulta {

    private String personaId;
    private String nombreEmpresa;
    private String grupoNomina;
    private String numeroEmpleado;
    private String nombreEmpleado;
    private String pagoComplementario;
}
