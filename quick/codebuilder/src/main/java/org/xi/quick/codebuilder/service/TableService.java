package org.xi.quick.codebuilder.service;

import org.xi.quick.codebuilder.model.TableModel;

import java.util.List;
import java.util.Set;

public interface TableService {

    /**
     * 获取所有表名
     *
     * @return
     */
    Set<String> getTableNameList();

    /**
     * 获取单表
     *
     * @param tableName
     * @return
     */
    TableModel getTable(String tableName);

    /**
     * 获取列表
     *
     * @param tableName
     * @return
     */
    List<TableModel> getTables(String tableName);
}
