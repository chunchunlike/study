package org.xi.dubbostudy.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xi.dubbostudy.service.TestService;

@Controller
@RequestMapping({ "/" })
public class HomeController {

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping({ "" })
    public String index(String name) {

        return testService.hello(name);
    }
}
