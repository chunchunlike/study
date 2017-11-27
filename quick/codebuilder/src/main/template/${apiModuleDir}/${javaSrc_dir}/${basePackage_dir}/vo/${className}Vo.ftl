package ${basePackage}.model;

<#include "/include/java_copyright.ftl">
public class ${table.tableClassName}Vo {

    <#list table.columns as column>
    /**
     * ${column.columnComment}
     */
    private ${column.columnFieldType} ${column.columnFieldNameFirstLower};

    </#list>

    <#list table.columns as column>
    /**
    * 设置${column.columnComment}
    */
    private void set${column.columnFieldName}(${column.columnFieldType} ${column.columnFieldNameFirstLower}) {
        this.${column.columnFieldNameFirstLower} = ${column.columnFieldNameFirstLower};
    }

    /**
    * 获取${column.columnComment}
    */
    private ${column.columnFieldType} get${column.columnFieldName}() {
        return this.${column.columnFieldNameFirstLower};
    }

    </#list>
}