package org.xi.quick.docbuilder.model;

import java.util.List;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/13 09:33
 */
public class ModelEntity {

    private String className;
    private List<FieldEntity> fields;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<FieldEntity> getFields() {
        return fields;
    }

    public void setFields(List<FieldEntity> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "ModelEntity{" +
                "className='" + className + '\'' +
                ", fields=" + fields +
                '}';
    }
}
