package com.sangwon97.aop.ex2.adv;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Packaging implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target)throws Throwable {
        log.info("포장을 진행한다.");
        
    }
    
    
}
