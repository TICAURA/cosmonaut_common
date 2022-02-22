package mx.com.ga.cosmonaut.common.dto;


import io.micronaut.core.annotation.Introspected;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.sql.Timestamp;

@Data
@Introspected
public class NcoChatColaboradorDto {
    private String nombre;
    private String apellido_pat;
    private String apellido_mat;
    private Integer chatColaboradorId;
    private String mensajes;
    private String conversacionId;
    private Timestamp fechaUltimoMensaje;
    private boolean esActual;
    private Integer usuarioId;
    private Integer centrocClienteId;
    private String nombreRrh;
    private Integer idUsuarioRrh;
    private String nombre_corto;
    private boolean atendido;
    private String descripcion;

}