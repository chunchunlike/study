package org.xi.quick.codebuilder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xi.quick.codebuilder.entity.Column;
import org.xi.quick.codebuilder.entity.Table;
import org.xi.quick.codebuilder.mapper.ColumnsMapper;
import org.xi.quick.codebuilder.mapper.TablesMapper;
import org.xi.quick.codebuilder.model.ColumnModel;
import org.xi.quick.codebuilder.model.TableModel;
import org.xi.quick.codebuilder.service.TableService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("tableService")
public class TableServiceImpl implements TableService {

    @Value("${database.name}")
    String databaseName;

    @Autowired
    private TablesMapper tablesMapper;

    @Autowired
    private ColumnsMapper columnsMapper;

    @Override
    public List<TableModel> getTables(String tableName) {

        List<Table> tables = tablesMapper.getTables(databaseName, tableName);
        List<TableModel> tableModels = new ArrayList<>();

        for (Table table : tables) {

            TableModel model = new TableModel(table);
            List<Column> columnList = columnsMapper.getColumns(databaseName, tableName);
            List<ColumnModel> columnModels =
                    columnList
                            .stream()
                            .map(entity -> new ColumnModel(entity))
                            .collect(Collectors.toList());
            model.setColumns(columnModels);

            tableModels.add(model);
        }
        return tableModels;
    }
}
