package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class EmpresaClientePrincipalConsulta {

    private Integer idEmpresa;
    private String nombreEmpresa;
}
