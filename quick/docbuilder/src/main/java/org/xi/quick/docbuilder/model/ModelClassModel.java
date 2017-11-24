package org.xi.quick.docbuilder.model;

import org.xi.quick.docbuilder.entity.FieldEntity;

import java.util.List;
import java.util.Properties;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/13 09:33
 */
public class ModelClassModel {

    private String className;
    private List<FieldEntity> fields;
    private Properties properties;

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

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "ModelClassModel{" +
                "className='" + className + '\'' +
                ", fields=" + fields +
                '}';
    }
}
