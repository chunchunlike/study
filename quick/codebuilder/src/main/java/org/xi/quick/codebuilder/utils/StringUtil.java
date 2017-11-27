package org.xi.quick.codebuilder.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/27 17:46
 */
public class StringUtil {

    public static String getFirstLower(String s) {

        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    /**
     * 获取驼峰命名
     *
     * @param s
     * @return
     */
    public static String getCamelCaseName(String s) {

        return getCamelCaseName(s, "_");
    }

    /**
     * 获取驼峰命名
     *
     * @param s
     * @param split
     * @return
     */
    public static String getCamelCaseName(String s, String split) {

        if (s == null || s.isEmpty()) return s;

        String[] tableNameSplit = s.split(split);

        return String.join("", Arrays.stream(tableNameSplit).map(o -> o.substring(0, 1).toUpperCase() + o.substring(1)).collect(Collectors.toList()));
    }
}
