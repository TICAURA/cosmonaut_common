package mx.com.ga.cosmonaut.common.dto.persona;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleEventosResponse {

    private String nombrePolitica;
    private int vacacionesUsadas;
    private int vacacionesDisponibles;
    private int incapacidadesUsadas;
    private int economicosUsados;
    private int economicosDisponibles;
    private boolean descuentoSeptimoDia;
    private boolean descuentoIncapacidad;
    private boolean descuentoFalta;
    private boolean primaVacacionalAniversario;
    private int diasEconomicos;
    private int diasVacaciones;
    private BigDecimal primaVacacional = BigDecimal.ZERO;
    private int diasAguinaldo;

}
