package org.xi.quick.codebuilder.model;

import org.xi.quick.codebuilder.entity.Table;

import java.util.List;

public class TableModel {

    private String databaseName;
    private String tableName;
    private String tableComment;

    private List<ColumnModel> columns;

    public TableModel() {

    }

    public TableModel(Table table) {
        this.databaseName = table.getTableSchema();
        this.tableName = table.getTableName();
        this.tableComment = table.getTableComment();
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }
}
