package org.xi.quick.codebuilder.utils;

import freemarker.template.TemplateException;
import org.xi.quick.codebuilder.model.FreemarkerModel;

import java.io.*;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/27 14:51
 */
public class FreemarkerUtil {

    /**
     * 生成输出
     *
     * @param outModel
     * @param dataModel
     * @throws IOException
     * @throws TemplateException
     */
    public static void generate(FreemarkerModel outModel, String tableName, Object dataModel, String encoding) throws IOException, TemplateException {

        String absolutePath = getFilePath(outModel, tableName);

        //创建文件路径
        DirectoryUtil.createIfNotExists(outModel.getAbsoluteDirectory());

        try (OutputStream stream = new FileOutputStream(absolutePath);
             Writer out = new OutputStreamWriter(stream, encoding)) {

            outModel.getTemplate().process(dataModel, out);
        }
    }

    public static void delete(FreemarkerModel outModel, String tableName) {

        String absolutePath = getFilePath(outModel, tableName);
        FileUtil.delete(absolutePath);
    }


    static String getFilePath(FreemarkerModel model, String tableName) {

        String absolutePath = model.getAbsolutePath();
        if (absolutePath.contains("${tableName}")) {
            absolutePath = absolutePath.replace("${tableName}", tableName);
        }
        return absolutePath;
    }
}
