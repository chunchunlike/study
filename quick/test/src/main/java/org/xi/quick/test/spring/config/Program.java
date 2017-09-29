package org.xi.quick.test.spring.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Program {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        Singer singer = (Singer) context.getBean("singer");
        singer.SayHello();
    }
}
