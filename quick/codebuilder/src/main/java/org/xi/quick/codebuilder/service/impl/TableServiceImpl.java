package org.xi.quick.codebuilder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xi.quick.codebuilder.entity.Column;
import org.xi.quick.codebuilder.entity.Table;
import org.xi.quick.codebuilder.mapper.ColumnsMapper;
import org.xi.quick.codebuilder.mapper.TablesMapper;
import org.xi.quick.codebuilder.model.ColumnModel;
import org.xi.quick.codebuilder.model.TableModel;
import org.xi.quick.codebuilder.service.TableService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TablesMapper tablesMapper;

    @Autowired
    private ColumnsMapper columnsMapper;

    @Override
    public TableModel getTable(String tableName) {
        return getTable(null, tableName);
    }

    @Override
    public TableModel getTable(String databaseName, String tableName) {
        Table table = tablesMapper.getTable(databaseName, tableName);
        TableModel model = new TableModel(table);
        List<Column> columnList = columnsMapper.getColumns(databaseName, tableName);
        List<ColumnModel> columnModels =
                columnList
                        .stream()
                        .map(entity -> new ColumnModel(entity))
                        .collect(Collectors.toList());
        model.setColumns(columnModels);
        return model;
    }
}
