package mx.com.ga.cosmonaut.common.dto;
import lombok.Data;

@Data
public class RespuestaGenericaGruop {

    private Object datos;
    private Object group;
    private boolean resultado;
    private String mensaje;

    public RespuestaGenericaGruop(Object datos,Object group, boolean resultado, String mensaje) {
        this.datos = datos;
        this.group = group;
        this.resultado = resultado;
        this.mensaje = mensaje;
    }

    public RespuestaGenericaGruop() {
    }
}

