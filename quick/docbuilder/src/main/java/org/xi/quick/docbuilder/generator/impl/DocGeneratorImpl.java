package org.xi.quick.docbuilder.generator.impl;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xi.quick.docbuilder.factory.FreemarkerConfigurationFactory;
import org.xi.quick.docbuilder.generator.DocGenerator;
import org.xi.quick.docbuilder.model.*;
import org.xi.quick.docbuilder.utils.GeneratorUtil;
import org.xi.quick.utils.io.DirectoryUtil;

import java.io.*;
import java.util.*;
import java.util.function.Function;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/13 09:32
 */
@Component("docGenerator")
public class DocGeneratorImpl implements DocGenerator {

    @Autowired
    String modelPath;

    @Autowired
    String voPath;

    @Autowired
    String parameterPath;

    @Autowired
    String apiPath;

    @Autowired
    String apiSubPackages;

    @Autowired
    String templatePath;

    @Autowired
    String outputPath;

    @Autowired
    Properties commonProperties;

    @Autowired
    GeneratorUtil generatorUtil;

    /**
     * 生成Model文档
     *
     * @throws IOException
     * @throws TemplateException
     */
    public void generateModelDoc() throws IOException, TemplateException {

        createModelDoc(modelPath, "models.html",
                fileName -> fileName.endsWith(".java") && !fileName.endsWith("Example.java"),
                file -> generatorUtil.getModelClassModel(file));
    }

    /**
     * 生成Vo文档
     *
     * @throws IOException
     * @throws TemplateException
     */
    public void generateVoDoc() throws IOException, TemplateException {

        createModelDoc(voPath, "vos.html",
                fileName -> fileName.endsWith(".java"),
                file -> generatorUtil.getVoClassModel(file));
    }

    /**
     * 生成Parameter文档
     *
     * @throws IOException
     * @throws TemplateException
     */
    public void generateParameterDoc() throws IOException, TemplateException {

        createModelDoc(parameterPath, "parameters.html",
                fileName -> fileName.endsWith("Parameter.java"),
                file -> generatorUtil.getParameterClassModel(file));
    }

    /**
     * 生成API文档
     *
     * @throws IOException
     * @throws TemplateException
     */
    public void generateApiDoc() throws IOException, TemplateException {


        freemarker.template.Configuration freeMarkerConfiguration = FreemarkerConfigurationFactory.createConfiguration(templatePath);
        Template template = freeMarkerConfiguration.getTemplate("api.ftl");


        for (String subPackage : apiSubPackages.split(",")) {
            String outFilePath = outputPath + "api/" + subPackage + "/";
            String dubboConfigOutFilePath = outputPath + "dubboConfig/";
            DirectoryUtil.createIfNotExists(outFilePath);
            DirectoryUtil.createIfNotExists(dubboConfigOutFilePath);

            File[] files = DirectoryUtil.getAllFiles(apiPath + subPackage);

            if (files != null) {
                List<ApiClassModel> apiEntities = new ArrayList<>();

                for (File file : files) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".java")) {

                        ApiClassModel entity = generatorUtil.getApiClassModel(file);
                        entity.setProperties(commonProperties);

                        OutputStream stream = new FileOutputStream(outFilePath + entity.getClassName() + ".html");
                        Writer out = new OutputStreamWriter(stream);
                        template.process(entity, out);

                        apiEntities.add(entity);
                    }
                }

                Template dubboConfigTemplate = freeMarkerConfiguration.getTemplate("dubboConfigXml.ftl");

                Map<Object, Object> dubboConfigMap = new HashMap<>();
                dubboConfigMap.put("apiEntityList", apiEntities);
                dubboConfigMap.putAll(commonProperties);

                OutputStream stream = new FileOutputStream(dubboConfigOutFilePath + subPackage + ".html");
                Writer out = new OutputStreamWriter(stream);
                dubboConfigTemplate.process(dubboConfigMap, out);
            }
        }

    }

    /**
     * 生成实体类文档
     *
     * @param path
     * @param outFileName
     * @param nameMatch
     * @param getEntity
     * @throws IOException
     * @throws TemplateException
     */
    private void createModelDoc(String path, String outFileName,
                                Function<String, Boolean> nameMatch,
                                Function<File, ModelClassModel> getEntity) throws IOException, TemplateException {

        freemarker.template.Configuration freeMarkerConfiguration = FreemarkerConfigurationFactory.createConfiguration(templatePath);
        Template template = freeMarkerConfiguration.getTemplate("models.ftl");

        List<String> classNameList = new ArrayList<>();
        List<ModelClassModel> modelList = new ArrayList<>();

        File[] files = DirectoryUtil.getAllFiles(path);
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();

                if (nameMatch.apply(fileName)) {

                    String className = fileName.substring(0, fileName.length() - 5);
                    classNameList.add(className);

                    ModelClassModel entity = getEntity.apply(file);
                    modelList.add(entity);

                    generatorUtil.MODEL_MAP.put(className, entity);
                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("classNameList", classNameList);
        map.put("classList", modelList);

        OutputStream stream = new FileOutputStream(outputPath + outFileName);
        Writer out = new OutputStreamWriter(stream, "UTF-8");
        template.process(map, out);
    }
}
