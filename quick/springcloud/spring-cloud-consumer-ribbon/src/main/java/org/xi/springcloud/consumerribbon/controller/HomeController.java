package org.xi.springcloud.consumerribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xi.springcloud.consumerribbon.service.CommonService;

@RestController
public class HomeController {

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/getProviderPort")
    public String getProviderPort() {
        return commonService.getProviderPort();
    }
}
