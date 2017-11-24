package org.xi.quick.docbuilder.entity;

import org.xi.quick.docbuilder.model.ModelClassModel;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/13 15:24
 */
public class ParameterTypeEntity {

    public ParameterTypeEntity(String parameterType, ModelClassModel parameter) {
        this.parameterType = parameterType;
        this.parameter = parameter;
    }

    private String parameterType;
    private ModelClassModel parameter;

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public ModelClassModel getParameter() {
        return parameter;
    }

    public void setParameter(ModelClassModel parameter) {
        this.parameter = parameter;
    }
}
