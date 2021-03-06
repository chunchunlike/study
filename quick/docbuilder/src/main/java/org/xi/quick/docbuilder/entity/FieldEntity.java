package org.xi.quick.docbuilder.entity;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/13 09:33
 */
public class FieldEntity {

    public FieldEntity() {
        this.fieldType = "";
        this.fieldName = "";
        this.remark = "";
        this.insertNotNull = false;
        this.updateNotNull = false;
    }

    private String fieldType;
    private String fieldName;
    private String remark;
    private Boolean insertNotNull;
    private Boolean updateNotNull;

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getInsertNotNull() {
        return insertNotNull;
    }

    public void setInsertNotNull(Boolean insertNotNull) {
        this.insertNotNull = insertNotNull;
    }

    public Boolean getUpdateNotNull() {
        return updateNotNull;
    }

    public void setUpdateNotNull(Boolean updateNotNull) {
        this.updateNotNull = updateNotNull;
    }

    @Override
    public String toString() {
        return "FieldEntity{" +
                "fieldType='" + fieldType + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", remark='" + remark + '\'' +
                ", insertNotNull=" + insertNotNull +
                ", updateNotNull=" + updateNotNull +
                '}';
    }
}
