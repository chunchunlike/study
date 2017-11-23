package org.xi.quick.docbuilder.model;

import java.util.List;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/13 09:34
 */
public class ApiEntity {

    private String className;
    private String packageName;
    private List<FunctionEntity> functions;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<FunctionEntity> getFunctions() {
        return functions;
    }

    public void setFunctions(List<FunctionEntity> functions) {
        this.functions = functions;
    }
}
