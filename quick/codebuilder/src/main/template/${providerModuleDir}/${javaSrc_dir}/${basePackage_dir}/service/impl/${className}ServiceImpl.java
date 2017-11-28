<#assign className = table.tableClassName>
<#assign classNameLower = table.tableClassNameFirstLower>
<#assign primaryKeyColumn = table.primaryKey>

package ${basePackage}.service.impl;

import org.xi.common.model.Pagination;
import ${basePackage}.mapper.${className}Mapper;
import ${basePackage}.model.${className};
import ${basePackage}.parameter.select.${className}SelectParameter;
import ${basePackage}.vo.${className}Vo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<#include "/include/java_copyright.ftl">
@Service("${classNameLower}Service")
@Transactional
public class ${className}ServiceImpl extends BaseServiceImpl<${className}> implements ${className}Service {

    @Autowired
    private ${className}Mapper ${classNameLower}Mapper;

    /**
     * 添加
     *
     * @param ${classNameLower}
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ${className} add${className}(${className} ${classNameLower}) {
        return super.insert(${classNameLower});
    }

    /**
     * 添加列表
     *
     * @param ${classNameLower}List
     * @return
    <#include "/include/author_info1.ftl">
     */
    @Override
    public ${className} add${className}List(List<${className}> ${classNameLower}List) {
        return super.insertList(${classNameLower}s);
    }

    /**
     * 根据主键更新
     *
     * @param ${classNameLower}
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ${className} update${className}(${className} ${classNameLower}) {
        return super.update(${classNameLower});
    }
    <#if table.hasIsActive>

    /**
     * 根据主键使之有效
     *
     * @param pk
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ${className} enable${className}ByPk(${primaryKeyColumn.columnFieldType} pk) {
        ${className} ${classNameLower} = new ${className}();
        ${classNameLower}.set${primaryKeyColumn.columnFieldName}(pk);
        ${classNameLower}.setIsActive(1);
        return super.update(${classNameLower});
    }

    /**
     * 根据主键列表使之有效
     *
     * @param pks
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ${className} enable${className}ByPkList(List<${primaryKeyColumn.columnFieldType}> pks) {
        return null;
    }

    /**
     * 根据主键使之无效
     *
     * @param pk
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ${className} disable${className}ByPk(${primaryKeyColumn.columnFieldType} pk) {
        ${className} ${classNameLower} = new ${className}();
        ${classNameLower}.set${primaryKeyColumn.columnFieldName}(pk);
        ${classNameLower}.setIsActive(0);
        return super.update(${classNameLower});
    }

    /**
     * 根据主键列表使之无效
     *
     * @param pks
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ${className} disable${className}ByPkList(List<${primaryKeyColumn.columnFieldType}> pks) {
        return null;
    }
    </#if>

    /**
     * 根据主键物理删除
     *
     * @param pk
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ${className} delete${className}ByPk(${primaryKeyColumn.columnFieldType} pk) {
        return super.deleteByPk(pk);
    }

    /**
     * 根据主键获取
     *
     * @param pk
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public ${className}Vo get${className}ByPk(${primaryKeyColumn.columnFieldType} pk) {
        ${className}Vo vo = get${className}ByPk(pk);
        return vo;
    }

    /**
     * 分页查询
     *
     * @param parameter
     * @param pagination
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    Pagination<${className}Vo> find${className}PageList(${className}SelectParameter parameter, Pagination pagination) {

        //先查询总数量
        PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
        //分页查询数据
        List<${className}Vo> list = ${classNameLower}Mapper.find${className}List(parameter);
        PageInfo page = new PageInfo(list);
        pagination.setContent(list);
        pagination.setDataNumber(page.getTotal());

        return pagination;
    }

}
