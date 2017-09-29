package org.xi.quick.test.spring.config;

import org.springframework.stereotype.Component;

@Component
public class SingerSayHello implements Say {

    @Override
    public void sayHello() {
        System.out.println("Singer say Hello");
    }
}
