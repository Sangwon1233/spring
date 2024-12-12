package com.sangwon97.aop.ex06;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class MyAdvice {
    @Pointcut("execution (* *(..)) && args(intValue)")
    public void hello(int inValue) {}
    @Pointcut("bean(myDependency)")
    public void beanPointcut() {}

    @Before("hello(inValue) && bean")
    public void simpleBefore(JoinPoint joinPoint , int inValue){
       if(inValue>5000){
            log.info(joinPoint.getSignature().getName());
       } 
    }
    @Around("execution(* bye(..))")
    public Object simpleAround(ProceedingJoinPoint pjp) throws Throwable{
        log.info("around before");
        Object o =pjp.proceed();
        log.info("around after");
        return o;
    }

    @After("execution(*bye(..))")
    public void simpleAfter(JoinPoint joinPoint, int inValue){
        log.info(joinPoint.getSignature().getName());
        log.info("에프터터");

    }
}
