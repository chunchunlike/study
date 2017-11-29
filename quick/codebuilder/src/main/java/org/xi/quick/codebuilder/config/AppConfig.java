package org.xi.quick.codebuilder.config;

import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.xi.quick.codebuilder.model.FreemarkerModel;
import org.xi.quick.codebuilder.utils.DirectoryUtil;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("${path.template}")
    String templatePath;

    @Value("${path.out}")
    String outPath;

    @Value("${path.common.properties}")
    String commonPropertiesPath;

    @Value("${codeEncoding}")
    String codeEncoding;

    @Value("${folder.ingored}")
    String iningoredFolder;

    @Bean(name = "commonPropertiesMap")
    public Map<Object, Object> getCommonPropertiesMap() {

        Map<Object, Object> commonPropertiesMap = new HashMap<>();
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(commonPropertiesPath);
             Reader reader = new InputStreamReader(inputStream, codeEncoding)) {
            properties.load(reader);
            properties.forEach((key, value) -> commonPropertiesMap.put(key, value));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        commonPropertiesMap.put("now", new Date());

        return commonPropertiesMap;
    }

    @Bean(name = "freeMarkerConfiguration")
    public freemarker.template.Configuration getConfiguration() throws IOException {

        File directory = new File(templatePath);

        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_24);
        cfg.setDirectoryForTemplateLoading(directory);
        cfg.setDefaultEncoding(codeEncoding);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return cfg;
    }

    /**
     * 所有模版
     *
     * @return
     * @throws IOException
     */
    @Bean(name = "allTemplates")
    public List<FreemarkerModel> getAllTemplates(freemarker.template.Configuration freeMarkerConfiguration,
                                                 Map<Object, Object> commonPropertiesMap) throws IOException {

        File directory = new File(templatePath);
        String dirAbsolutePath = directory.getAbsolutePath();

        List<File> files = DirectoryUtil.getAllFiles(templatePath);
        List<FreemarkerModel> result = new ArrayList<>();

        for (File file : files) {

            if(file.isHidden()) continue;

            String templateRelativePath = file.getAbsolutePath().substring(dirAbsolutePath.length() + 1);
            if (isIngoredFolder(templateRelativePath) || !templateRelativePath.contains("${className}")) continue;

            Template template = freeMarkerConfiguration.getTemplate(templateRelativePath, codeEncoding);

            String fileAbsolutePath = getActualPath(templateRelativePath, commonPropertiesMap);
            FreemarkerModel outModel = new FreemarkerModel(fileAbsolutePath, template);
            result.add(outModel);
        }

        return result;
    }

    /**
     * 所有运行一次的模版
     *
     * @return
     * @throws IOException
     */
    @Bean(name = "allOnceTemplates")
    public List<FreemarkerModel> getAllOnceTemplates(freemarker.template.Configuration freeMarkerConfiguration,
                                                     Map<Object, Object> commonPropertiesMap) throws IOException {

        File directory = new File(templatePath);
        String dirAbsolutePath = directory.getAbsolutePath();

        List<File> files = DirectoryUtil.getAllFiles(templatePath);
        List<FreemarkerModel> result = new ArrayList<>();

        for (File file : files) {

            if(file.isHidden()) continue;

            String templateRelativePath = file.getAbsolutePath().substring(dirAbsolutePath.length() + 1);
            if (isIngoredFolder(templateRelativePath) || templateRelativePath.contains("${className}")) continue;

            Template template = freeMarkerConfiguration.getTemplate(templateRelativePath, codeEncoding);

            String fileAbsolutePath = getActualPath(templateRelativePath, commonPropertiesMap);
            FreemarkerModel outModel = new FreemarkerModel(fileAbsolutePath, template);
            result.add(outModel);
        }

        return result;
    }

    /**
     * 判断是否是已经忽略的文件夹
     *
     * @param templateRelativePath
     * @return
     */
    private boolean isIngoredFolder(String templateRelativePath) {

        String[] iningoredFolders = iningoredFolder.split(",");

        for (String folder : iningoredFolders) {
            if (!folder.endsWith("/"))
                folder += "/";
            if (templateRelativePath.startsWith(folder) || templateRelativePath.contains("/" + folder))
                return true;
        }

        return false;
    }


    /**
     * 获取文件输出实际绝对路径
     *
     * @param relativePath
     * @return
     */
    private String getActualPath(String relativePath, Map<Object, Object> commonPropertiesMap) {

        Pattern pattern = Pattern.compile("\\$\\{[^\\}]*\\}");
        Matcher matcher = pattern.matcher(relativePath);
        while (matcher.find()) {
            String group = matcher.group();
            String key = group.substring(2, group.length() - 1);

            boolean isDir = key.endsWith("_dir");
            if (isDir) {
                key = key.substring(0, key.length() - 4);
            }

            Object value = commonPropertiesMap.get(key);

            if (value != null) {
                relativePath = isDir
                        ? relativePath.replace(group, ((String) value).replaceAll("\\.", "/"))
                        : relativePath.replace(group, (String) value);
            }
        }

        File directory = new File(outPath);
        return directory.getAbsolutePath() + "/" + relativePath;
    }
}
