package mx.com.ga.cosmonaut.common.dto.mail;

import lombok.Data;

import java.util.Base64;

@Data
public class MailAttachment {

    protected String mimeType;
    protected String fileName;
    protected String disposition="attachment";
    protected String content;

    public byte[] getDecodedContent() {
        return Base64.getDecoder().decode(content);
    }

    public void setDecodedContent(byte[] content) {
        this.content = Base64.getEncoder().encodeToString(content);
    }

}
