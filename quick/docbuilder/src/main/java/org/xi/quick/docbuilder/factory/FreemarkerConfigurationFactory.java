package org.xi.quick.docbuilder.factory;

import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/13 11:56
 */
public class FreemarkerConfigurationFactory {

    public static freemarker.template.Configuration createConfiguration(String templatePath) throws IOException {

        File directory = new File(templatePath);

        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_24);
        configuration.setDirectoryForTemplateLoading(directory);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setDefaultEncoding("UTF-8");

        return configuration;
    }
}
