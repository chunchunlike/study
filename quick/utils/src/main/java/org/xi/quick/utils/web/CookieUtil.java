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
    private final int timeout = 30 * 24 * 60 * 60;
    private final Cookie[] cookies;
    private final Map<String, Cookie> cookieMap;

    public CookieUtil(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;

        cookieMap = new HashMap<String, Cookie>();
        cookies = request.getCookies();

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
    }

    public Map<String, Cookie> getCookieMap() {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }

    public Cookie[] getAllCookies() {

        return request.getCookies();
    }

    public void addCookie(String name, String value) {

        addCookie(name, value, "/", timeout);
    }

    public void addCookie(String name, String value, String path) {

        addCookie(name, value, path, timeout);
    }

    public void addCookie(String name, String value, String path, int timeout) {

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(timeout);
        cookie.setPath(path);

        response.addCookie(cookie);
    }

    public void delCookie(String name) {

        Cookie cookie = getCookie(name);

        if (null != cookie) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    public void delCookies(String[] names) {

        for (String name : names) {
            delCookie(name);
        }
    }

    public void delAllCookies() {

        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    public void editCookie(String name, String value) {

        Cookie cookie = getCookie(name);

        if (null != cookie) {
            cookie.setValue(value);
            response.addCookie(cookie);
        } else {
            addCookie(name, value);
        }
    }

    public Cookie getCookie(String name) {

        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }
    }

    public String getCookieValue(String name) {

        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name).getValue();
        } else {
            return null;
        }
    }

}
