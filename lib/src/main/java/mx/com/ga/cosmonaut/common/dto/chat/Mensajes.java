package mx.com.ga.cosmonaut.common.dto.chat;

import lombok.Data;


import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Mensajes {
    private String mensaje;
    private Date fecha;
    private Integer usuarioId;
    private String nombre;
}
