package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class SuaAltasConsulta {

    private Integer kardexId;
    private Integer idEmpresa;
    private String registroPatronal;
    private String nss;
    private String rfc;
    private String curp;
    private String apellidoPat;
    private String apellidoMat;
    private String nombreEmpleado;
    private String tipoTrabajador;
    private String jornadaReducida;
    private String fechaMovimiento;
    private String sdi;
    private String claveSubdelegacion;
    private String credInfonavit;
    private String inicioDescuento;
    private String tipoDescuento;
    private String valorDescuento;
}
