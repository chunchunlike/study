package org.xi.quick.codebuilder.config;

import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("${template.path}")
    String templatePath;

    @Value("${commonPropertiesPath}")
    String commonPropertiesPath;

    @Bean(name="commonPropertiesMap")
    public Map<Object, Object> getCommonPropertiesMap() {

        Map<Object, Object> commonPropertiesMap = new HashMap<>();
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(commonPropertiesPath)) {
            properties.load(inputStream);
            properties.forEach((key, value) -> commonPropertiesMap.put(key, value));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commonPropertiesMap;
    }

    @Bean(name = "freeMarkerConfiguration")
    public freemarker.template.Configuration getConfiguration() throws IOException {

        File directory = new File(templatePath);

        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_24);
        cfg.setDirectoryForTemplateLoading(directory);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return cfg;
    }
}
