package mx.com.ga.cosmonaut.common.entity.temporal;

import lombok.Data;

@Data
public class CargaMasivaPTU {

    private Long ptuId;
    private String numeroEmleado;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private Long diasTrabajados;
    private Double totalBrutoPtu;
    private Integer centroClienteId;
    private boolean esCorrecto;
    private String errores;

}
