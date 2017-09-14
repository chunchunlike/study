package org.xi.quick.test.task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class SpringTaskTest {

    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        //TaskCase taskCase = (TaskCase) context.getBean("taskCase");

        System.in.read();
    }
}
