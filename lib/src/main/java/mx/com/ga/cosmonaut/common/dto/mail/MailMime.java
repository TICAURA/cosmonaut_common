package mx.com.ga.cosmonaut.common.dto.mail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MailMime {

    PLAIN("text/plain"),
    HTML("text/html");

    protected final String valor;
}
