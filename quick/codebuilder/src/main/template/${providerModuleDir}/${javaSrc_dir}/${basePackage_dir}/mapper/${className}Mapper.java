<#assign className = table.tableClassName>
<#assign classNameLower = table.tableClassNameFirstLower>
<#assign primaryKey = table.primaryKey>
package ${basePackage}.mapper;

import ${basePackage}.model.${className};
import ${basePackage}.parameter.select.${className}SelectParameter;
import ${basePackage}.vo.${className}Vo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

<#include "/include/java_copyright.ftl">
@Repository
public interface ${className}Mapper extends BaseMapper<${className}> {

    /**
     * 根据主键获取
     *
     * @param pk
     * @return
    <#include "/include/author_info1.ftl">
     */
    ${className}Vo get${className}ByPk(@Param("pk") ${primaryKey.columnFieldType} pk);

    /**
     * 查询
     *
     * @param parameter
     * @return
    <#include "/include/author_info1.ftl">
     */
    List<${className}Vo> find${className}List(${className}SelectParameter parameter);
}
