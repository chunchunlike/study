package org.xi.quick.codebuilder.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xi.quick.codebuilder.entity.Table;

@Mapper
public interface TablesMapper {

    Table getTable(@Param("databaseName") String databaseName, @Param("tableName") String tableName);
}
