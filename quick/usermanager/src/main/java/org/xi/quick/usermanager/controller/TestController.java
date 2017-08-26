package org.xi.quick.usermanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xi.quick.usermanager.model.test.AjaxBindModel;

@Controller
@RequestMapping({"/test"})
public class TestController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index() {
        return "test/index";
    }

    @RequestMapping(value = "ajaxBindModel", method = RequestMethod.GET)
    public String ajaxBindModel() {
        return "test/ajaxBindModel";
    }

    @ResponseBody
    @RequestMapping(value = "ajaxBindModel", method = RequestMethod.POST)
    public String ajaxBindModel(AjaxBindModel model) {

        System.out.println(model.getId());
        System.out.println(model.getName());

        return model.getId() + model.getName();
    }
}
