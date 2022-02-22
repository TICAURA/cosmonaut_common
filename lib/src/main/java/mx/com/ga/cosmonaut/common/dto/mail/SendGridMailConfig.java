package mx.com.ga.cosmonaut.common.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendGridMailConfig {

    protected String apiKey;
    protected String sender;

}
