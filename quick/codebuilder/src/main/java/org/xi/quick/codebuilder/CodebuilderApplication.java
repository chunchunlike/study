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
    Map<Object, Object> commonPropertiesMap;

    @Autowired
    TableService tableService;

    @Override
    public void run(String... strings) throws Exception {

        List<TableModel> tables = tableService.getTables(null);
        for (TableModel table : tables) {
            System.out.println("============================================");
            System.out.println(table.getTableName());
            generate(table);
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
}
