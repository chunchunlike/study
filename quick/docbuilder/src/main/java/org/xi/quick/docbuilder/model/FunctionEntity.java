package org.xi.quick.docbuilder.model;

import java.util.List;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/13 09:34
 */
public class FunctionEntity {

    public FunctionEntity() {
        this.name = "";
        this.remark = "";
        this.author = "";
        this.type = "";
        this.desc = "";
    }

    private String name;
    private String remark;
    private String author;
    private String type;

    private ParameterEntity returnType;
    private List<ParameterEntity> params;

    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ParameterEntity getReturnType() {
        return returnType;
    }

    public void setReturnType(ParameterEntity returnType) {
        this.returnType = returnType;
    }

    public List<ParameterEntity> getParams() {
        return params;
    }

    public void setParams(List<ParameterEntity> params) {
        this.params = params;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
