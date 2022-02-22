package mx.com.ga.cosmonaut.common.dto.nomina.historica;

import lombok.Data;

@Data
public class FiltradoRequest {

    private String nombre;
    private String clavePeriodo;
    private String fechaInicio;

}
