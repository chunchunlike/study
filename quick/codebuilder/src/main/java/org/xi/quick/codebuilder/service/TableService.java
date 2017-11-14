package org.xi.quick.codebuilder.service;

import org.xi.quick.codebuilder.model.TableModel;

public interface TableService {

    TableModel getTable(String tableName);

    TableModel getTable(String databaseName, String tableName);
}
