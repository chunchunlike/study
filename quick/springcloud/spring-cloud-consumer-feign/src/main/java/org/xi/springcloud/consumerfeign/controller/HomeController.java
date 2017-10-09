package org.xi.springcloud.consumerfeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xi.springcloud.consumerfeign.service.CommonService;

@RestController
public class HomeController {

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/getProviderPort")
    public String getProviderPort() {
        return commonService.getProviderPort();
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(String name) {
        return commonService.sayHello(name);
    }
}
