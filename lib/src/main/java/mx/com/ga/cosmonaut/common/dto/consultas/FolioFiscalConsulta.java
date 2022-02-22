package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class FolioFiscalConsulta {

    private Integer nominaXperiodoId;
    private Integer personaId;
    private String nombreNomina;
    private String clavePeriodo;
    private String fechaInicio;
    private String fechaFin;
    private String rfc;
    private String primerApellido;
    private String segundoApellido;
    private String nombre;
    private String folioFiscal;
    private String fechaTimbrado;
}
