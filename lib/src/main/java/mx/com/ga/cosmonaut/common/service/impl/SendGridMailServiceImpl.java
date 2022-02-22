package mx.com.ga.cosmonaut.common.service.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mx.com.ga.cosmonaut.common.dto.mail.*;
import mx.com.ga.cosmonaut.common.service.MailService;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Singleton
public class SendGridMailServiceImpl implements MailService {

    @Setter
    private SendGridMailConfig config;

    @Override
    public MailResult enviarCorreo(MailObject correo) {
        Mail mail = new Mail();

        mail.addPersonalization(generatePersonalization(correo));
        if (correo.getFrom() != null) {
            mail.setFrom(new Email(correo.getFrom()));
        } else {
            mail.setFrom(new Email(config.getSender()));
        }

        if (correo.getTextContent() != null) {
            mail.addContent(new Content(MailMime.PLAIN.getValor(), correo.getTextContent()));
        }
        if (correo.getHtmlContent() != null) {
            mail.addContent(new Content(MailMime.HTML.getValor(), correo.getHtmlContent()));
        }

        if (correo.getAttachments() != null) {
            for (MailAttachment at : correo.getAttachments()) {
                Attachments att = new Attachments();
                att.setContent(at.getContent());
                att.setDisposition(at.getDisposition());
                att.setFilename(at.getFileName());
                att.setContentId(at.getFileName());
                att.setType(at.getMimeType());
                mail.addAttachments(att);
            }
        }

        SendGrid sg = new SendGrid(config.getApiKey());
        Request req = new Request();

        req.setMethod(Method.POST);
        req.setEndpoint("mail/send");
        MailResult response = new MailResult();
        try {
            req.setBody(mail.build());
            Response resp = sg.api(req);
            if (resp.getStatusCode() >= 200 && resp.getStatusCode() < 300) {
                response.setCorreosEnviados(correo.destinatariosTotales());
                response.setExito(true);
            } else {
                response.setCorreosEnviados(0);
                response.setExito(false);
            }
            response.setCodigoProveedor(String.valueOf(resp.getStatusCode()));
            response.setMensajeProveedor(resp.getBody());
            return response;
        } catch (IOException e) {
            response.setExito(false);
            response.setMensajeProveedor(e.getMessage());
            log.error("Error al enviar correo por sendgrid ",e);
        }
        return response;
    }

    private Personalization generatePersonalization (MailObject mailObject) {
        Personalization personalization = new Personalization();
        if (mailObject.hasTo()) {
            for (String to : mailObject.getTo()) {
                personalization.addTo(new Email(to));
            }
        }
        if (mailObject.hasCc()) {
            for (String cc : mailObject.getCc()) {
                personalization.addCc(new Email(cc));
            }
        }
        if (mailObject.hasBcc()) {
            for (String bcc : mailObject.getBcc()) {
                personalization.addCc(new Email(bcc));
            }
        }
        personalization.setSubject(mailObject.getSubject());
        return personalization;
    }

    @Override
    public List<MailResult> enviarCorreos(List<MailObject> correos) {
        List<MailResult> resultados = new ArrayList<>();
        for(MailObject correo : correos) {
            resultados.add(enviarCorreo(correo));
        }
        return resultados;
    }

}
