package org.hssounz.redditclonebackend.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service @AllArgsConstructor
public class MailContentBuilder {
    private final TemplateEngine templateEngine;
    public String build(String message, String url) {
        Context context = new Context();
        context.setVariables(Map.of("message", message, "url", url));
        return templateEngine.process("mail-template", context);
    }
}
