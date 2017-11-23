package org.xi.quick.docbuilder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/11 12:19
 */
@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("${project.path}")
    String projectPath;

    @Value("${project.java.src}")
    String projectJavaSrc;

    @Value("${project.basePackage}")
    String projectBasePackage;

    @Value("${project.api.subPackages}")
    String projectApiSubPackages;

    @Value("${path.template}")
    String templatePath;

    @Value("${path.output}")
    String outputPath;

    @Value("${function.addmatch}")
    String addmMatch;

    @Value("${function.updatematch}")
    String updateMatch;

    @Value("${commonPropertiesPath}")
    String commonPropertiesPath;

    @Bean("commonProperties")
    public Properties getCommonProperties() {
        Properties properties = new Properties();
        try (InputStream in = new FileInputStream(commonPropertiesPath)) {
            properties.load(in);
        } catch (IOException e) {

        }
        return properties;
    }

    private String getpackagePath() {
        return projectBasePackage.replaceAll("\\.", "/");
    }

    @Bean("modelPath")
    public String getModelPath() {
        return projectPath + projectJavaSrc + getpackagePath() + "/model/";
    }

    @Bean("voPath")
    public String getVoPath() {
        return projectPath + projectJavaSrc + getpackagePath() + "/vo/";
    }

    @Bean("parameterPath")
    public String getParameterPath() {
        return projectPath + projectJavaSrc + getpackagePath() + "/parameter/";
    }

    @Bean("apiPath")
    public String getApiPath() {
        return projectPath + projectJavaSrc + getpackagePath() + "/api/";
    }

    @Bean("apiSubPackages")
    public String getApiPaths() {
        return projectApiSubPackages;
    }

    @Bean("templatePath")
    public String getTemplatePath() {
        return templatePath;
    }

    @Bean("outputPath")
    public String getOutputPath() {
        return outputPath;
    }

    @Bean("addmMatch")
    public String getAddmMatch() {
        return addmMatch;
    }

    @Bean("updateMatch")
    public String getUpdateMatch() {
        return updateMatch;
    }
}
