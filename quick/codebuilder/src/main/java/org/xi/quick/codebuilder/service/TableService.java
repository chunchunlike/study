package org.xi.quick.codebuilder.service;

import org.xi.quick.codebuilder.model.TableModel;

import java.util.List;

public interface TableService {

    List<TableModel> getTables(String tableName);
}
