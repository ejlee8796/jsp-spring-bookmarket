/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.aspectj.lang.ProceedingJoinPoint
 *  org.aspectj.lang.annotation.Around
 *  org.aspectj.lang.annotation.Aspect
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Component
 */
package com.company.aop;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    @Around(value="execution(* com.company.spring0716.*Controller.*(..)) or execution(* com.company.service.*Impl.*(..)) or execution(* com.company.persistence.*Impl.*(..))")
    public Object logPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String name = "";
        if (type.contains("Controller")) {
            name = "Controller";
        } else if (type.contains("Service")) {
            name = "Service";
        } else if (type.contains("DAO")) {
            name = "DAO";
        }
        long end = System.currentTimeMillis();
        logger.info("---------------------------------------------");
        logger.info("---------------------------------------------");
        logger.info(String.valueOf(name) + type + "." + proceedingJoinPoint.getSignature().getName() + "()");
        logger.info("para : " + Arrays.toString(proceedingJoinPoint.getArgs()));
        logger.info("running time : " + (end - start));
        logger.info(String.valueOf(name) + type + "." + proceedingJoinPoint.getSignature().getName() + "()");
        logger.info("---------------------------------------------");
        logger.info("---------------------------------------------");
        return result;
    }
}
