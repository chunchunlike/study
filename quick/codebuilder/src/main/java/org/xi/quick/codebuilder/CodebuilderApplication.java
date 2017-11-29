package org.xi.quick.codebuilder;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xi.quick.codebuilder.model.FreemarkerModel;
import org.xi.quick.codebuilder.model.TableModel;
import org.xi.quick.codebuilder.service.TableService;
import org.xi.quick.codebuilder.utils.FileUtil;
import org.xi.quick.codebuilder.utils.FreemarkerUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CodebuilderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CodebuilderApplication.class, args);
    }

    @Value("${codeEncoding}")
    String codeEncoding;

    @Autowired
    List<FreemarkerModel> allTemplates;

    @Autowired
    List<FreemarkerModel> allOnceTemplates;

    @Autowired
    Map<Object, Object> commonPropertiesMap;

    @Autowired
    TableService tableService;

    @Override
    public void run(String... strings) throws Exception {
        generateAllOnce();
        generateAll();
    }

    /**
     * 生成所有生成一次的类
     *
     * @throws IOException
     * @throws TemplateException
     */
    void generateAllOnce() throws IOException, TemplateException {

        for (FreemarkerModel template : allOnceTemplates) {
            Map<Object, Object> dataModel = new HashMap<>();
            dataModel.putAll(commonPropertiesMap);
            FreemarkerUtil.generate(template, dataModel, codeEncoding);
        }
    }

    /**
     * 生成所有数据类
     *
     * @throws IOException
     * @throws TemplateException
     */
    void generateAll() throws IOException, TemplateException {

        //生成所有基类
        generateAllOnce();

        List<TableModel> tables = tableService.getTables(null);

        for (TableModel table : tables) {
            generate(table);
        }
    }

    /**
     * 生成数据类
     *
     * @param tableNames
     * @throws IOException
     * @throws TemplateException
     */
    void generate(String... tableNames) throws IOException, TemplateException {

        for (String tableName : tableNames) {

            List<TableModel> tables = tableService.getTables(tableName);
            if (tables == null || tables.isEmpty()) {
                System.out.println("表" + tableName + "不存在");
                return;
            }
            for (TableModel table : tables) {
                System.out.println("============================================");
                System.out.println(table.getTableName());
                generate(table);
            }
        }
    }

    void generate(TableModel model) throws IOException, TemplateException {

        for (FreemarkerModel template : allTemplates) {
            Map<Object, Object> dataModel = new HashMap<>();
            dataModel.putAll(commonPropertiesMap);
            dataModel.put("table", model);
            FreemarkerUtil.generate(template, model.getTableClassName(), dataModel, codeEncoding);
        }
    }

    /**
     * 删除数据类
     *
     * @param tableNames
     */
    void delete(String... tableNames) {

        for (String tableName : tableNames) {

            List<TableModel> tables = tableService.getTables(tableName);
            for (TableModel table : tables) {
                for (FreemarkerModel template : allTemplates) {
                    FreemarkerUtil.delete(template, table.getTableClassName());
                }
            }
        }
    }

    /**
     * 删除所有数据类
     */
    void deleteAll() {

        List<TableModel> tables = tableService.getTables(null);
        for (TableModel table : tables) {
            for (FreemarkerModel template : allTemplates) {
                FreemarkerUtil.delete(template, table.getTableClassName());
            }
        }
    }

    /**
     * 删除所有生成一次的类
     */
    void deleteAllOnce() {

        for (FreemarkerModel template : allOnceTemplates) {
            FreemarkerUtil.delete(template);
        }
    }
}
