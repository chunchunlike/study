package org.xi.quick.codebuilder.model;

import freemarker.template.Template;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/27 12:16
 */
public class FreemarkerModel {

    public FreemarkerModel(String absolutePath, Template template) {
        this.absolutePath = absolutePath;
        this.template = template;
    }

    private String absolutePath;

    private String packagePath;
    private boolean aggregate;

    private Template template;

    public String getAbsolutePath() {
        return absolutePath;
    }

    public String getAbsoluteDirectory() {
        return absolutePath.substring(0, absolutePath.lastIndexOf("/"));
    }

    public Template getTemplate() {
        return template;
    }

    public boolean isAggregate() {
        return aggregate;
    }

    public void setAggregate(boolean aggregate) {
        this.aggregate = aggregate;
    }
}
