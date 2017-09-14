package org.xi.quick.filemanager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.xi.quick.utils.security.MD5Util;
import org.xi.quick.utils.web.CookieUtil;

public class LoginInterceptor implements HandlerInterceptor {

    private static final String LOGIN_URL = "/account/login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        CookieUtil cookieHelper = new CookieUtil(request, response);

        String userId = cookieHelper.getCookieValue("ID");
        String timestamp = cookieHelper.getCookieValue("login");
        String ss = cookieHelper.getCookieValue("ss");

        if (userId == null || timestamp == null || !MD5Util.encrypt(userId+timestamp).equals(ss)) {
            response.sendRedirect(request.getContextPath() + LOGIN_URL);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
