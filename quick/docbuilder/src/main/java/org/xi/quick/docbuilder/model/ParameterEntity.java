package org.xi.quick.docbuilder.model;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/13 15:24
 */
public class ParameterEntity {

    public ParameterEntity(String parameterType, ModelEntity parameter) {
        this.parameterType = parameterType;
        this.parameter = parameter;
    }

    private String parameterType;
    private ModelEntity parameter;

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public ModelEntity getParameter() {
        return parameter;
    }

    public void setParameter(ModelEntity parameter) {
        this.parameter = parameter;
    }
}
