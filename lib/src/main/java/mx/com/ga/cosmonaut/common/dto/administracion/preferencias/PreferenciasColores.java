package mx.com.ga.cosmonaut.common.dto.administracion.preferencias;

import lombok.Data;

@Data
public class PreferenciasColores {
    private Long preferenciaId;
    private String colormenu;
    private String colorfondo;
    private boolean mostrarlogosistema;
    private Long clienteId;
}
