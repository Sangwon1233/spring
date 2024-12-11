package com.sangwon97.aop.ex3;

import org.springframework.aop.Advisor;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.sangwon97.aop.ex2.adv.Packaging;
import com.sangwon97.aop.ex3.adv.ThrowLog;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MartClient {
    public static void main(String[] args){
         //1. ProxyFactory 생성
        ProxyFactory factory = new ProxyFactory();
        //2. target을 MartImpl로 지정
        factory.setTarget(new MartImpl());
        //3. ex02의 packaging을 advice로 지정
        factory.addAdvice(new Packaging());
         //4. ex03의  ThrowLog을 advice로 지정
        factory.addAdvice(new ThrowLog());

        //5. proxy객체 생성  후 getProduct호출
        try{
            Mart mart2 = (Mart)factory.getProxy();
            mart2.getProduct("한민이 최고");
        }
        catch(RuntimeException r){
            // log.error(r.getMessage());
            // r.printStackTrace();

        }
       
    }
}