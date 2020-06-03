package com.luv2code.springdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CrmLoggingAspect {

    //setup pointcut declaration
    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    //combined pointcut declaration
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    //@Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        //wyświetlenie sygnatury metody przechwyconej
        String signature = joinPoint.getSignature().toShortString();
        log.info("=====> in @Before: calling method: " + signature);

        //wyswietlenie argumentów metody przechwyconej
        Object[] args = joinPoint.getArgs();
        for (Object tempArg : args) {
            log.info("=====> in @Before: method arguments: " + tempArg);
        }
    }

    //@AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()", returning="result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        //wyświetlenie sygnatury metody przechwyconej
        String signature = joinPoint.getSignature().toShortString();
        log.info("=====> in @AfterReturning: calling method: " + signature);

        //wyswietlenie wyniku działania metody przechwyconej
        log.info("=====> in @AfterReturning: result of method: " + result);
    }
}
