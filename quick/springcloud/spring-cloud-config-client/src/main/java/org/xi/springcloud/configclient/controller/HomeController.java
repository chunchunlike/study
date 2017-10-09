package org.xi.springcloud.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("${name}")
    String name;

    @RequestMapping(value="name")
    public String name() {

        return name;
    }
}
