package org.xi.quick.test.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskCase {

    @Scheduled(cron = "0/5 * * * * ?")
    public void doSomething() {
        System.out.println("Hello, doSomething at " + new Date());
    }

    @Scheduled(fixedRate = 1000)
    public void doSomething2() {
        System.out.println("Hello, doSomething2 at " + new Date());
    }
}
