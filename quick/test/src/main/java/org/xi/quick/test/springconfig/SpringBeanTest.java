package org.xi.quick.test.springconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBeanTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        Singer singer = (Singer) context.getBean("singer");
        singer.SayHello();
    }
}
