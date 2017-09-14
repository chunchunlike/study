package org.xi.quick.filemanager.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xi.quick.filemanager.entity.UserEntity;
import org.xi.quick.filemanager.mapper.UserMapper;
import org.xi.quick.filemanager.model.account.LoginModel;
import org.xi.quick.filemanager.model.account.RegisterModel;
import org.xi.quick.utils.security.MD5Util;
import org.xi.quick.utils.web.CookieUtil;

@Controller
@RequestMapping({ "/account" })
public class AccountController {

    @Autowired
    private UserMapper userMapper;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private CookieUtil cookieUtil;

    @ModelAttribute
    private void initCookieHelper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        cookieUtil = new CookieUtil(request, response);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "re", defaultValue = "/") String re, Model model) {

        String userId = cookieUtil.getCookieValue("ID");
        String timestamp = cookieUtil.getCookieValue("login");
        String ss = cookieUtil.getCookieValue("ss");

        if (userId != null && timestamp != null && MD5Util.encrypt(userId + timestamp).equals(ss)) {
            return "redirect:" + re;
        }

        model.addAttribute("model", new LoginModel());
        model.addAttribute("re", re);
        return "account/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "re", defaultValue = "/") String re,
            @Valid @ModelAttribute("model") LoginModel model, Errors errors) {

        if (errors.hasErrors()) {
            return "account/login";
        }

        UserEntity entity = userMapper.selectOne(model.getUsername(), model.getPassword());
        if (entity != null) {
            CookieUtil cookieUtil = new CookieUtil(request, response);

            long timestamp = Calendar.getInstance().getTimeInMillis();
            cookieUtil.setCookie("ID", entity.getUserId() + "");
            cookieUtil.setCookie("login", timestamp + "");
            cookieUtil.setCookie("ss", MD5Util.encrypt(entity.getUserId() + "" + timestamp));
            return "redirect:" + re;
        }

        return "account/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@RequestParam(value = "re", defaultValue = "/") String re, Model model) {

        model.addAttribute("model", new RegisterModel());
        model.addAttribute("re", re);
        return "account/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "re", defaultValue = "/") String re,
                           @Valid @ModelAttribute("model") RegisterModel model, Errors errors) {

        if (errors.hasErrors()) {
            return "account/register";
        }

        UserEntity entity = new UserEntity(model.getUsername(), model.getPassword(), model.getEmail(), model.getPhone());
        userMapper.insert(entity);
        Integer userId = entity.getUserId();
        if (userId!=null && userId>0) {
            long timestamp = Calendar.getInstance().getTimeInMillis();
            cookieUtil.setCookie("ID", userId + "");
            cookieUtil.setCookie("login", timestamp + "");
            cookieUtil.setCookie("ss", MD5Util.encrypt(entity.getUserId() + "" + timestamp));
            return "redirect:" + re;
        }
        return "account/register";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(@RequestParam(value = "re", defaultValue = "/") String re) {

        cookieUtil.clearCookies();
        return "redirect:" + re;
    }

}
