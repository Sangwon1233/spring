package com.sangwon97.ex04;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.sangwon97.aop.ex2.adv.Seasoning;
import com.sangwon97.aop.ex3.adv.SimpleAdvs;

import lombok.extern.log4j.Log4j2;
@Log4j2
public class Client {
    public static void main(String[] args) {
        ProxyFactory factory =  new ProxyFactory(new First());
        factory.addAdvice(new Seasoning()); //염지하는 메소드
        //어드바이스는 실제 할 일
        ((First)factory.getProxy()).one();
        ((First)factory.getProxy()).two();
        log.info("+++++++++++++++++++++++++++++++++++");
        factory =  new ProxyFactory(new First());
        factory.addAdvisor(new DefaultPointcutAdvisor(new SimpleAdvs(),new Seasoning()));

        ((First)factory.getProxy()).one();
        ((First)factory.getProxy()).two();
    }
}
