package org.xi.quick.test.springconfig;

import org.springframework.stereotype.Component;

@Component
public class SingerSayHello implements Say {

    @Override
    public void sayHello() {
        System.out.println("Singer say Hello");
    }
}
