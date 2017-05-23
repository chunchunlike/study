package org.xi.quick.usermanager.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.xi.quick.utils.web.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Xi on 5/23/2017.
 */
public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected CookieUtil cookieUtil;

    @ModelAttribute
    protected void initCookieHelper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        cookieUtil = new CookieUtil(request, response);
    }
}
