package org.xi.dubbostudy.provider.service;

import org.xi.dubbostudy.service.TestService;

public class TestServiceImpl implements TestService {
    public String hello(String name) {
        System.out.println("hello2");
        return "hello " + name;
    }
}
