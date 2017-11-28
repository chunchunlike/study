<#assign className = table.tableClassName>
<#assign classNameLower = table.tableClassNameFirstLower>
<#assign primaryKeyColumn = table.primaryKey>

package ${basePackage}.service;

import com.cloudyoung.ec.common.model.base.Result;
import com.cloudyoung.ec.common.model.page.Pagination;
import ${basePackage}.model.${className};
import ${basePackage}.parameter.select.${className}SelectParameter;
import ${basePackage}.vo.${className}Vo;

import java.util.List;

<#include "/include/java_copyright.ftl">
public interface ${className}Service {

    /**
     * 添加
     *
     * @param ${classNameLower}
     * @return
     <#include "/include/author_info1.ftl">
     */
    ${className} add${className}(${className} ${classNameLower});

    /**
     * 添加列表
     *
     * @param ${classNameLower}List
     * @return
    <#include "/include/author_info1.ftl">
     */
    ${className} add${className}List(List<${className}> ${classNameLower}List);

    /**
     * 根据主键更新
     *
     * @param ${classNameLower}
     * @return
     <#include "/include/author_info1.ftl">
     */
    ${className} update${className}(${className} ${classNameLower});
    <#if table.hasIsActive>

    /**
     * 根据主键使之有效
     *
     * @param pk
     * @return
     <#include "/include/author_info1.ftl">
     */
    ${className} enable${className}ByPk(${primaryKeyColumn.columnFieldType} pk);

    /**
     * 根据主键列表使之有效
     *
     * @param pks
     * @return
     <#include "/include/author_info1.ftl">
     */
    ${className} enable${className}ByPkList(List<${primaryKeyColumn.columnFieldType}> pks);

    /**
     * 根据主键使之无效
     *
     * @param pk
     * @return
     <#include "/include/author_info1.ftl">
     */
    ${className} disable${className}ByPk(${primaryKeyColumn.columnFieldType} pk);

    /**
     * 根据主键列表使之无效
     *
     * @param pks
     * @return
     <#include "/include/author_info1.ftl">
     */
    ${className} disable${className}ByPkList(List<${primaryKeyColumn.columnFieldType}> pks);
    </#if>

    /**
     * 根据主键物理删除
     *
     * @param pk
     * @return
     <#include "/include/author_info1.ftl">
     */
    ${className} delete${className}ByPk(${primaryKeyColumn.columnFieldType} pk);

    /**
     * 根据主键获取
     *
     * @param pk
     * @return
     <#include "/include/author_info1.ftl">
     */
    ${className}Vo get${className}ByPk(${primaryKeyColumn.columnFieldType} pk);

    /**
     * 分页查询
     *
     * @param parameter
     * @param pagination
     * @return
     <#include "/include/author_info1.ftl">
     */
    Pagination<${className}Vo> find${className}PageList(${className}SelectParameter parameter, Pagination pagination);

}
