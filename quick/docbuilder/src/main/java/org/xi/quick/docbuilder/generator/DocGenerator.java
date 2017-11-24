package org.xi.quick.docbuilder.generator;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/24 10:44
 */
public interface DocGenerator {
    
    /**
     * 生成Model文档
     *
     * @throws IOException
     * @throws TemplateException
     */
    void generateModelDoc() throws IOException, TemplateException;

    /**
     * 生成Vo文档
     *
     * @throws IOException
     * @throws TemplateException
     */
    void generateVoDoc() throws IOException, TemplateException;

    /**
     * 生成Parameter文档
     *
     * @throws IOException
     * @throws TemplateException
     */
    void generateParameterDoc() throws IOException, TemplateException;

    /**
     * 生成API文档
     *
     * @throws IOException
     * @throws TemplateException
     */
    void generateApiDoc() throws IOException, TemplateException;
}
