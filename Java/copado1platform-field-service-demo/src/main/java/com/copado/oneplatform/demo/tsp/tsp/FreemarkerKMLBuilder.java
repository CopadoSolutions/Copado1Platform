package com.copado.oneplatform.demo.tsp.tsp;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
public class FreemarkerKMLBuilder {

    public String build(Route route) throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(FreemarkerKMLBuilder.class, "/templates/");
        cfg.setDefaultEncoding("UTF-8");

        Map<String, Object> input = new HashMap<>();
        input.put("locations", Locations.getLocations());
        input.put("route", route);

        Template template = cfg.getTemplate("base.kml.ftl");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer consoleWriter = new OutputStreamWriter(outputStream);
        template.process(input, consoleWriter);
        outputStream.flush();
        outputStream.close();
        return new String(outputStream.toByteArray());
    }
}
