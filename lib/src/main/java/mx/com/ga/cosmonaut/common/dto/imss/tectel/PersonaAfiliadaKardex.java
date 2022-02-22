package mx.com.ga.cosmonaut.common.dto.imss.tectel;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Introspected
@Data
public class PersonaAfiliadaKardex {
    private Integer personaId;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private Integer kardexColaboradorId;
    private String numeroLote;
    private String idProceso;
}
