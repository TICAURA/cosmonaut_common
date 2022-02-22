package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.dto.mail.MailObject;
import mx.com.ga.cosmonaut.common.dto.mail.MailResult;
import mx.com.ga.cosmonaut.common.dto.mail.SendGridMailConfig;

import java.util.List;

public interface MailService {

    void setConfig(SendGridMailConfig config);

    MailResult enviarCorreo(MailObject correo);

    List<MailResult> enviarCorreos(List<MailObject> correos);

}
