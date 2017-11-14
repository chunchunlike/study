package org.xi.quick.docbuilder.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.xi.quick.docbuilder.factory.FreemarkerConfigurationFactory;
import org.xi.quick.docbuilder.model.*;
import org.xi.quick.utils.io.DirectoryUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/13 09:32
 */
@Component
@PropertySource("classpath:config.properties")
public class DocGenerator {

    @Value("${path.model}")
    String modelPath;

    @Value("${path.vo}")
    String voPath;

    @Value("${path.parameter}")
    String parameterPath;

    @Value("${path.api}")
    String apiPath;

    @Value("${path.template}")
    String templatePath;

    @Value("${path.output}")
    String outputPath;

    @Value("${function.addmatch}")
    String addmMtch;

    @Value("${function.updatematch}")
    String updateMatch;

    Map<String, ModelEntity> modelMap = new HashMap<>();

    public ApiEntity getApiEntity(File file) {

        try (FileReader reader = new FileReader(file.getPath());
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            List<FunctionEntity> functions = new ArrayList<>();

            //进入接口内部
            while ((line = br.readLine()) != null) {

                if (line.startsWith("public interface")) {
                    break;
                }
            }

            FunctionEntity functionEntity = new FunctionEntity();
            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty()) continue;

                if (line.startsWith("*") && !line.endsWith("*/")) {
                    Pattern pattern = Pattern.compile("\\*\\s?@");
                    Pattern patternAuthor = Pattern.compile("\\*\\s?@author\\s?");
                    Pattern patternDesc = Pattern.compile("\\*\\s?@(描述|Description)(：|:)?\\s?");
                    Matcher matcher;
                    if ((matcher = pattern.matcher(line)).find()) {
                        if ((matcher = patternAuthor.matcher(line)).find()) {
                            functionEntity.setAuthor(line.replaceAll("\\*\\s?@author\\s?", ""));
                        } else if ((matcher = patternDesc.matcher(line)).find()) {
                            functionEntity.setRemark(line.replaceAll("\\*\\s?@(描述|Description)(：|:)?\\s?", ""));
                        }
                    } else {
                        if (!line.trim().equals("*")) {
                            functionEntity.setRemark(line.replace("*", "").trim());
                        }
                    }
                } else if (line.startsWith("Result")) {
                    if (!line.endsWith(";")) {
                        String tmpLine;
                        while ((tmpLine = br.readLine()) != null) {
                            line += tmpLine;
                            if (tmpLine.endsWith(";")) break;
                        }
                    }
                    this.setFunctionParams(functionEntity, line);
                    String func = line.split("\\(")[0];
                    int lastIndex = func.lastIndexOf(" ");
                    functionEntity.setName(func.substring(lastIndex).trim());
                    functionEntity.setType(this.getFunctionType(functionEntity.getName()));

                    //获取返回类型
                    String returnType = func.substring(0, lastIndex).trim();
                    functionEntity.setReturnType(new ParameterEntity(getTypeForHtml(returnType), modelMap.get(this.getClassType(returnType))));

                    functionEntity.setDesc(this.getTypeForHtml(line.replaceAll("\\s{1,}", " ")));

                    functions.add(functionEntity);
                    functionEntity = new FunctionEntity();
                }

            }

            String fileName = file.getName();
            String className = fileName.substring(0, fileName.length() - 5);

            ApiEntity apiEntity = new ApiEntity();
            apiEntity.setClassName(className);
            apiEntity.setFunctions(functions);
            return apiEntity;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String getFunctionType(String functionName) {
        for (String s : addmMtch.split(",")) {
            if (functionName.startsWith(s)) return "add";
        }
        for (String s : updateMatch.split(",")) {
            if (functionName.startsWith(s)) return "update";
        }
        return null;
    }

    public void generateModelDoc() throws IOException, TemplateException {
        generateModelDoc(modelPath, outputPath + "models.html");
    }

    public void generateVoDoc() throws IOException, TemplateException {
        generateModelDoc(voPath, outputPath + "vos.html");
    }

    public void generateParameterDoc() throws IOException, TemplateException {
        generateModelDoc(parameterPath, outputPath + "parameters.html");
    }

    /**
     * 生成API文档
     *
     * @param templatePath
     * @param modelDirectory
     * @param outFilePath
     * @throws IOException
     * @throws TemplateException
     */
    public void generateApiDoc() throws IOException, TemplateException {
        String outFilePath = outputPath + "api/";
        DirectoryUtil.createIfNotExists(outFilePath);

        freemarker.template.Configuration freeMarkerConfiguration = FreemarkerConfigurationFactory.createConfiguration(templatePath);
        Template template = freeMarkerConfiguration.getTemplate("api.html");

        File[] files = DirectoryUtil.getAllFiles(apiPath);

        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (fileName.endsWith(".java")) {

                    ApiEntity entity = this.getApiEntity(file);

                    OutputStream stream = new FileOutputStream(outFilePath + entity.getClassName() + ".html");
                    Writer out = new OutputStreamWriter(stream);
                    template.process(entity, out);
                }
            }
        }
    }

    public ModelEntity getModelEntity(File file) {

        List<FieldEntity> fields = new ArrayList<>();

        try (FileReader reader = new FileReader(file.getPath());
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            String tempRemark = ""; ////region注释
            FieldEntity fieldEntity = new FieldEntity();

            //进入类内部
            while ((line = br.readLine()) != null) {

                if (line.startsWith("public class")) {
                    break;
                }
            }

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty()) continue;

                if (line.startsWith("//region")) {
                    tempRemark = line.replace("//region", "");
                } else if (line.startsWith("//endregion")) {
                    tempRemark = "";
                } else if (line.startsWith("*") && !line.endsWith("*/")) {
                    fieldEntity.setRemark(line.replace("*", "").trim());
                } else if (line.startsWith("@CheckFieldAnnotation")) {
                    this.setFieldInsertAndUpdate(fieldEntity, line);
                } else if (line.startsWith("private") && line.endsWith(";") && !line.contains("orderByMap")) {
                    line = line.substring("private".length());
                    line = line.split("=")[0].replace(";", "");

                    int lastIndex = line.lastIndexOf(" ");

                    fieldEntity.setFieldName(line.substring(lastIndex).trim());
                    fieldEntity.setFieldType(this.getTypeForHtml(line.substring(0, lastIndex).trim()));
                    if (fieldEntity.getRemark() == null || fieldEntity.getRemark().isEmpty()) {
                        fieldEntity.setRemark(this.getRemark(tempRemark, fieldEntity.getFieldType(), fieldEntity.getFieldName()));
                    }

                    fields.add(fieldEntity);
                    fieldEntity = new FieldEntity();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setClassName(file.getName().replace(".java", ""));
        modelEntity.setFields(fields);
        return modelEntity;
    }

    /**
     * 生成实体类文档
     *
     * @param templatePath
     * @param modelDirectory
     * @param outFilePath
     * @throws IOException
     * @throws TemplateException
     */
    public void generateModelDoc(String modelDirectory, String outFilePath)
            throws IOException, TemplateException {

        freemarker.template.Configuration freeMarkerConfiguration = FreemarkerConfigurationFactory.createConfiguration(templatePath);
        Template template = freeMarkerConfiguration.getTemplate("models.html");

        File[] files = DirectoryUtil.getAllFiles(modelDirectory);
        List<String> classNameList = new ArrayList<>();
        List<ModelEntity> modelList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (fileName.endsWith(".java") && !fileName.endsWith("Example.java")) {

                    String className = fileName.substring(0, fileName.length() - 5);
                    classNameList.add(className);

                    ModelEntity entity = this.getModelEntity(file);
                    modelList.add(entity);

                    modelMap.put(className, entity);
                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("classNameList", classNameList);
        map.put("classList", modelList);

        OutputStream stream = new FileOutputStream(outFilePath);
        Writer out = new OutputStreamWriter(stream);
        template.process(map, out);
    }

    /**
     * 设置字段是否必须
     *
     * @param field
     * @param line
     */
    private void setFieldInsertAndUpdate(FieldEntity field, String line) {
        Pattern pattern = Pattern.compile("\\(.*\\)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String matchStr = matcher.group();
            String[] array = matchStr.substring(1, matchStr.length() - 1).split(",");
            for (String param : array) {
                param = param.trim();
                if (param.startsWith("insertNotNull")) {
                    field.setInsertNotNull(param.split("=")[1].trim().equals("true"));
                }
                if (param.trim().startsWith("updateNotNull")) {
                    field.setUpdateNotNull(param.split("=")[1].trim().equals("true"));
                }
            }
        }
    }

    /**
     * 为Parameter生成字段备注
     *
     * @param remark
     * @param fieldType
     * @param fieldName
     * @return
     */
    private String getRemark(String remark, String fieldType, String fieldName) {
        if (fieldName.endsWith("List")) {
            return remark + "列表";
        } else if (fieldName.endsWith("Min")) {
            if (fieldType.equals("Date")) {
                return "开始" + remark;
            } else {
                return "最小" + remark;
            }
        } else if (fieldName.endsWith("Max")) {
            if (fieldType.equals("Date")) {
                return "结束" + remark;
            } else {
                return "最大" + remark;
            }
        } else if (fieldName.endsWith("StartWith")) {
            return remark + " (开始匹配）";
        } else if (fieldName.endsWith("Like")) {
            return remark + " (泛匹配）";
        } else if (fieldType.equals("String")) {
            return remark + " (完全匹配）";
        } else {
            return remark;
        }
    }

    /**
     * 设置参数列表
     *
     * @param function
     * @param line
     */
    private void setFunctionParams(FunctionEntity function, String line) {

        Pattern pattern = Pattern.compile("\\(.*\\)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {

            List<ParameterEntity> params = new ArrayList<>();
            String matchStr = matcher.group();
            String[] array = matchStr.substring(1, matchStr.length() - 1).split(",");
            for (String param : array) {
                String paramType = param.split("\\s{1,}")[0];
                params.add(new ParameterEntity(this.getTypeForHtml(paramType), modelMap.get(this.getClassType(paramType))));
            }
            function.setParams(params);
        }
    }

    /**
     * 获取给HTML展示的类型
     *
     * @param type
     * @return
     */
    private String getTypeForHtml(String type) {
        return type.replace("<", "&lt;").replace(">", "&gt;");
    }

    /**
     * 获取要展示的类型，去掉泛型
     *
     * @param type
     * @return
     */
    private String getClassType(String type) {

        Pattern pattern = Pattern.compile("<[^(<|>)]*>");
        Matcher matcher;
        while ((matcher = pattern.matcher(type)).find()) {
            String tmpStr = matcher.group();
            type = tmpStr.substring(1, tmpStr.length() - 1);
        }
        String[] typeArr = type.split("\\s?,\\s?");

        return typeArr[typeArr.length - 1];
    }
}
