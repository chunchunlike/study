package org.xi.common.utils.database;

import org.xi.common.annotation.InsertNotNull;
import org.xi.common.annotation.UpdateNotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/28 18:28
 */
public class OperationCheckUtil {

    /**
     * 获取新增验证消息列表
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<String> checkInsert(T t) {

        List<String> messageList = new ArrayList<>();

        Class c = t.getClass();

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            InsertNotNull annotation = field.getAnnotation(InsertNotNull.class);
            if (annotation != null && annotation.required()) {
                field.setAccessible(true);
                Object obj = null;
                try {
                    obj = field.get(t);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (isNullOrEmpty(obj)) {
                    messageList.add(annotation.message());
                }
            }
        }

        return messageList;
    }

    /**
     * 获取更新验证消息列表
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<String> checkUpdate(T t) {

        List<String> messageList = new ArrayList<>();

        Class c = t.getClass();

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            UpdateNotNull annotation = field.getAnnotation(UpdateNotNull.class);
            if (annotation != null && annotation.required()) {
                field.setAccessible(true);
                Object obj = null;
                try {
                    obj = field.get(t);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (isNullOrEmpty(obj)) {
                    messageList.add(annotation.message());
                }
            }
        }

        return messageList;
    }

    static boolean isNullOrEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        }
        if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        }
        if (object instanceof Map) {
            return ((Map) object).isEmpty();
        }
        if (object instanceof Object[]) {
            Object[] objects = (Object[]) object;
            if (objects.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < objects.length; i++) {
                if (!isNullOrEmpty(objects[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }
}
