package org.xi.quick.codebuilder.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.xi.quick.codebuilder.utils.StringUtil;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/29 16:08
 */
@Component
@Aspect
public class GeneratorAspect {

    /**
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.xi.quick.codebuilder.service.GeneratorService.deleteAllOnce(..))")
    public Object deleteAllOnceAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("正在删除所有需要的基本类...");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("删除所有需要的基本类完成");

        return object;
    }


    /**
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.xi.quick.codebuilder.service.GeneratorService.generateAllOnce(..))")
    public Object generateAllOnceAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("正在生成需要的基本类...");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("生成所有需要的基本类完成");

        return object;
    }


    /**
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.xi.quick.codebuilder.service.GeneratorService.deleteAll(..))")
    public Object deleteAllAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("正在删除所有类...");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("删除所有类完成");

        return object;
    }


    /**
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.xi.quick.codebuilder.service.GeneratorService.generateAll(..))")
    public Object generateAllAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("正在生成所有类...");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("生成所有类完成");

        return object;
    }


    /**
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.xi.quick.codebuilder.service.GeneratorService.delete(..))")
    public Object deleteAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();
        String tables = StringUtil.join(",", (Object[])args[0]);

        System.out.println("正在删除" + tables + "相关的类...");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("删除" + tables + "相关的类完成");

        return object;
    }


    /**
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.xi.quick.codebuilder.service.GeneratorService.generate(..))")
    public Object generateAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();
        String tables = StringUtil.join(",", (Object[])args[0]);

        System.out.println("正在生成" + tables + "相关的类...");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("生成" + tables + "相关的类完成");

        return object;
    }
}
