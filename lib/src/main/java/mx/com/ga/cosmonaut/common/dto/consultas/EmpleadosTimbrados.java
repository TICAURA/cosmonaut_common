package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Introspected
@Data
public class EmpleadosTimbrados {
    private Integer nominaXperiodoId;
    private String nombreNomina;
    private String fechaTimbrado;
    private Boolean esExtraordinaria;
}
