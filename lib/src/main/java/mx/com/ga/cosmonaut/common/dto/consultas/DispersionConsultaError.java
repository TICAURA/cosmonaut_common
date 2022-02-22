package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Introspected
public class DispersionConsultaError {

    private String nomina;
    private String numeroEmpleado;
    private String nombre;
    private String rfc;
    private String banco;
    private String clave;
    private boolean estatus;
    private String observaciones;
    private Timestamp fechaDispersion;

}
