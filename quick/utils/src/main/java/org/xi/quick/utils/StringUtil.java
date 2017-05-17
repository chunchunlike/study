package org.xi.quick.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Xi on 5/17/2017.
 */
public class StringUtil {

    /**
     * 判断字符串是否为null或者空字符串
     *
     * @param s 输入字符串
     * @return
     */
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    /**
     * 判断字符串是否为null或者空白字符串
     *
     * @param s 输入字符串
     * @return
     */
    public static boolean isNullOrWhiteSpace(String s) {
        return s == null || s.trim().isEmpty();
    }


    /**
     * 是否是邮箱
     *
     * @param s 输入字符串
     * @return
     */
    public static boolean isEmail(String s) {
        Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
