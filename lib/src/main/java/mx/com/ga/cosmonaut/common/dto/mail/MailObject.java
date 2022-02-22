package mx.com.ga.cosmonaut.common.dto.mail;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MailObject {

    protected String apikey;

    protected String from;
    protected String[] to;
    protected String[] cc;
    protected String[] bcc;

    protected String subject;
    protected String textContent;
    protected String htmlContent;

    protected List<MailAttachment> attachments = new ArrayList<>();

    public void addAttachment(MailAttachment at) {
        this.attachments.add(at);
    }

    public void setSimpleTo(String to) {
        this.to = new String[] {to};
    }
    public void setSimpleCc(String cc) {
        this.cc = new String[] {cc};
    }
    public void setSimpleBcc(String bcc) {
        this.bcc = new String[] {bcc};
    }

    public boolean hasTo() {
        return to!=null && to.length>0;
    }
    public boolean hasCc() {
        return cc!=null && cc.length>0;
    }
    public boolean hasBcc() {
        return bcc!=null && bcc.length>0;
    }
    public int destinatariosTotales() {
        int total = 0;
        total+=hasTo()?to.length:0;
        total+=hasCc()?to.length:0;
        total+=hasBcc()?to.length:0;
        return total;
    }

}
