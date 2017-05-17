package org.xi.quick.utils.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xi on 5/17/2017.
 */
public class CookieUtil {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final int timeout;

    public CookieUtil(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;
        timeout = 30 * 24 * 60 * 60;
    }

    /**
     * 获取cookieMap
     * @return
     */
    public Map<String, Cookie> getCookieMap() {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }

    /**
     * 获取cookie值的Map
     * @return
     */
    public Map<String, String> getCookieValueMap() {
        Map<String, String> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }

        return cookieMap;
    }

    /**
     * 设置cookie
     * @param name  cookie名称
     * @param value cookie值
     */
    public void setCookie(String name, String value) {

        Cookie cookie = getCookie(name);

        if (null != cookie) {
            cookie.setValue(value);
            response.addCookie(cookie);
        } else {
            setCookie(name, value, "/", timeout);
        }
    }

    /**
     * 设置cookie
     * @param name  cookie名称
     * @param value cookie值
     * @param path  cookie路径
     */
    public void setCookie(String name, String value, String path) {

        Cookie cookie = getCookie(name);

        if (null != cookie) {
            cookie.setValue(value);
            cookie.setPath(path);
            response.addCookie(cookie);
        } else {
            setCookie(name, value, path, timeout);
        }
    }

    /**
     *  设置cookie
     * @param name      cookie名称
     * @param value     cookie值
     * @param path      cookie路径
     * @param timeout   cookie过期时长
     */
    public void setCookie(String name, String value, String path, int timeout) {

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(timeout);
        cookie.setPath(path);

        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     * @param name  cookie名称
     */
    public void delCookie(String name) {

        Cookie cookie = getCookie(name);

        if (null != cookie) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * 删除cookie
     * @param names cookie名称数组
     */
    public void delCookies(String[] names) {

        Map<String, Cookie> cookieMap = getCookieMap();
        for (String name : names) {
            if(cookieMap.containsKey(name)) {
                Cookie cookie = cookieMap.get(name);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    /**
     * 清理cookie
     */
    public void clearCookies() {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * 获取cookie
     * @param name  cookie名称
     * @return
     */
    public Cookie getCookie(String name) {

        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)) return cookie;
            }
        }
        return null;
    }

    /**
     * 获取cookie的值
     * @param name  cookie名称
     * @return
     */
    public String getCookieValue(String name) {

        Cookie cookie = getCookie(name);
        return null == cookie ? null : cookie.getValue();
    }

}
