package org.xi.quick.test.spring.aop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PlayAspect {

    /**
     * 设置标识
     */
    @Pointcut("execution(* org.xi.quick.test.spring.aop.aopinterface*.*.*(..))")
    public void play() {
    }


    /**
     * 方法执行前执行
     */
    @Before("play()")
    public void PlayBefore() {
        System.out.println("play before");
    }

    /**
     * 环绕方法执行，proceedingJoinPoint.proceed()是执行环绕的方法
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.xi.quick.test.spring.aop.aopinterface*.*.*(..))")
    //@After("play()")
    public Object PlayAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Gson gson = new GsonBuilder().create();

        System.out.println(proceedingJoinPoint.getSignature().getName() + "，输入参数：" + gson.toJson(proceedingJoinPoint.getArgs()));
        Object object = proceedingJoinPoint.proceed();
        System.out.println(proceedingJoinPoint.getSignature().getName() + "，返回：" + gson.toJson(object));

        return object;
    }

    /**
     * 方法执行后执行
     */
    @After("play()")
    public void PlayAfter() {
        System.out.println("play after");
    }

    /**
     * 方法返回后执行
     */
    @AfterReturning("play()")
    public void PlayAfterReturning() {
        System.out.println("play after returning value");
    }

    /**
     * 方法抛出异常后执行
     */
    @AfterThrowing("play()")
    public void PlayAfterThrowing() {
        System.out.println("play after throwing exception");
    }
}
