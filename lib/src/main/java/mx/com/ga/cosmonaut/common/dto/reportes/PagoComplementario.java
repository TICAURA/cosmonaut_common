package mx.com.ga.cosmonaut.common.dto.reportes;

import lombok.Data;

@Data
public class PagoComplementario {

    private String numeroEmpleado;
    private String nombreEmpleado;
    private String primerApellidoEmpleado;
    private String segundoApellidoEmpleado;
    private Integer grupoNomina;
    private Integer idEmpresa;
    private Integer idEmpresaActual;

}
