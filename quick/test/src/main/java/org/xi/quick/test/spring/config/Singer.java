package org.xi.quick.test.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Singer {

    @Autowired
    private Say say;

    public void SayHello() {
        say.sayHello();
    }
}
