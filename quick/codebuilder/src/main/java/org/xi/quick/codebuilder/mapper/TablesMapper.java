package org.xi.quick.codebuilder.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xi.quick.codebuilder.entity.Table;

import java.util.List;

@Mapper
public interface TablesMapper {

    List<Table> getTables(@Param("databaseName") String databaseName, @Param("tableName") String tableName);
}
