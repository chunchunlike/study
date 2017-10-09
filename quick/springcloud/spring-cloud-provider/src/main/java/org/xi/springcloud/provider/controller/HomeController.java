package org.xi.springcloud.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {


    @Value("${server.port}")
    private String port;

    @RequestMapping("/getProviderPort")
    public String getProviderPort() {
        return "provider port: " + port;
    }
}
