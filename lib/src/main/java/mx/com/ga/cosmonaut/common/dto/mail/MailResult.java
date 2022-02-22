package mx.com.ga.cosmonaut.common.dto.mail;

import lombok.Data;

@Data
public class MailResult {

    protected boolean exito;
    protected Integer correosEnviados;
    protected String[] destinatariosRechazados;
    protected String codigoProveedor;
    protected String mensajeProveedor;

}
