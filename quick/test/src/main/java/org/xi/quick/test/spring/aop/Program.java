package org.xi.quick.test.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.xi.quick.test.spring.aop.aopinterface.CdPlayer;

public class Program {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        CdPlayer player = (CdPlayer) context.getBean("cdPlayer");
        int i = player.read("one");
        System.out.println("play count: " + i);

        player.play("one");
    }
}
