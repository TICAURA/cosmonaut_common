package mx.com.ga.cosmonaut.common.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class Politica {

    private Integer politicaId;    
    private String nombre;
    private String nombreCorto;
    private Short diasEconomicos;
    private Boolean primaAniversario;
    private Boolean descuentaFaltas;
    private Boolean descuentaIncapacidades;
    private Boolean costoValesRestaurante;
    private boolean descuentoPropDia;
    private boolean esActivo;
    private String centrocClienteId;
    private String razonSocial;
    private Integer calculoAntiguedadId;
    private Boolean esEstandar;
    private Integer count;
}
