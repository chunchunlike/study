package org.xi.quick.docbuilder.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author 郗世豪（xish@cloud-young.com）
 * @date 2017/11/20 17:21
 */
@Component
@Aspect
public class GeneratorAspect {

    @Around(value = "execution(* org.xi.quick.docbuilder.generator.impl.DocGeneratorImpl.generateModelDoc(..))")
    public Object generateModelDocAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("开始生成Model文档...");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("生成Model文档结束...");

        return object;
    }

    @Around(value = "execution(* org.xi.quick.docbuilder.generator.impl.DocGeneratorImpl.generateVoDoc(..))")
    public Object generateVoDocAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("开始生成Vo文档...");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("生成Vo文档结束...");

        return object;
    }

    @Around(value = "execution(* org.xi.quick.docbuilder.generator.impl.DocGeneratorImpl.generateParameterDoc(..))")
    public Object generateParameterDocAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("开始生成Parameter文档...");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("生成Parameter文档结束...");

        return object;
    }

    @Around(value = "execution(* org.xi.quick.docbuilder.generator.impl.DocGeneratorImpl.generateApiDoc(..))")
    public Object generateApiDocAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("开始生成API文档...");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("生成API文档结束...");

        return object;
    }

}
