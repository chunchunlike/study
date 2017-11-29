package org.xi.quick.codebuilder.service.impl;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xi.quick.codebuilder.functionalinterface.BinaryConsumer;
import org.xi.quick.codebuilder.model.FreemarkerModel;
import org.xi.quick.codebuilder.model.TableModel;
import org.xi.quick.codebuilder.service.GeneratorService;
import org.xi.quick.codebuilder.service.TableService;
import org.xi.quick.codebuilder.utils.FreemarkerUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/29 16:31
 */
@Service("generatorService")
public class GeneratorServiceImpl implements GeneratorService {

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


    //region 生成一次的类

    /**
     * 生成所有生成一次的类
     */
    @Override
    public void generateAllOnce() {

        for (FreemarkerModel template : allOnceTemplates) {
            Map<Object, Object> dataModel = new HashMap<>();
            dataModel.putAll(commonPropertiesMap);
            try {
                FreemarkerUtil.generate(template, dataModel, codeEncoding);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除所有生成一次的类
     */
    @Override
    public void deleteAllOnce() {

        for (FreemarkerModel template : allOnceTemplates) {
            FreemarkerUtil.delete(template);
        }
    }

    //endregion

    /**
     * 生成所有数据类
     */
    @Override
    public void generateAll() {
        dataLoop((template, table) -> generate(template, table));
    }

    /**
     * 生成数据类
     *
     * @param tableNames
     */
    @Override
    public void generate(String... tableNames) {
        dataLoop((template, table) -> generate(template, table), tableNames);
    }

    /**
     * 删除所有数据类
     */
    @Override
    public void deleteAll() {
        dataLoop((template, table) -> FreemarkerUtil.delete(template, table.getTableClassName()));
    }

    /**
     * 删除数据类
     *
     * @param tableNames
     */
    @Override
    public void delete(String... tableNames) {
        dataLoop((template, table) -> FreemarkerUtil.delete(template, table.getTableClassName()), tableNames);
    }

    private void generate(FreemarkerModel template, TableModel table) {

        Map<Object, Object> dataModel = new HashMap<>();
        dataModel.putAll(commonPropertiesMap);
        dataModel.put("table", table);

        try {
            FreemarkerUtil.generate(template, table.getTableClassName(), dataModel, codeEncoding);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void dataLoop(BinaryConsumer<FreemarkerModel, TableModel> consumer, String... tableNames) {

        List<TableModel> tables;
        if (tableNames == null || tableNames.length == 0) {
            tables = tableService.getTables(null);
        } else {
            tables = new ArrayList<>();
            for (String tableName : tableNames) {
                TableModel tableModel = tableService.getTable(tableName);
                if (tableModel != null) {
                    tables.add(tableModel);
                }
            }
        }

        for (TableModel table : tables) {
            for (FreemarkerModel template : allTemplates) {
                consumer.accept(template, table);
            }
        }
    }
}
