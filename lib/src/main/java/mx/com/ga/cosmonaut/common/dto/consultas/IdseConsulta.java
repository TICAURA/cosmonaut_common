package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class IdseConsulta {

    private Integer kardexId;
    private String idEmpresa;
    private Integer imssId;
    private String registroPatronal;
    private String nss;
    private String primerApellido;
    private String segundoApellido;
    private String nombres;

    private String sbc;
    private String tipoTrabajador;
    private String tipoSalario;
    private String jornadaReducida;
    private String fechaMovimiento;
    private String medicinaFamiliar;
    private String fillerBlanco;
    private String tipoMovimiento;
    private String guia;
    private String claveTrabajador;
    private String curp;
    private String identificadorFormato;
    private String motivoBaja;
}
