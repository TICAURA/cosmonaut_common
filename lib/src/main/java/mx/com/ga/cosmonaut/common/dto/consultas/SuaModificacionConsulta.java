package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class SuaModificacionConsulta {

    private Integer kardexId;
    private Integer idEmpresa;
    private String registroPatronal;
    private String nss;
    private String tipoMovimiento;
    private String fechaMovimiento;
    private String fillerBlancoOcho;
    private String numeroDias;
    private String sbc;
}
