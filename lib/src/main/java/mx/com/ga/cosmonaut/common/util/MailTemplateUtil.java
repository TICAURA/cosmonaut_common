package mx.com.ga.cosmonaut.common.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class MailTemplateUtil {

    @Inject
    private TemplateEngine thymeleafTemplateEngine;

    public String renderHtml(String template, Map<String, Object> variables) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(variables);
        return thymeleafTemplateEngine.process(template, thymeleafContext);
    }


}
