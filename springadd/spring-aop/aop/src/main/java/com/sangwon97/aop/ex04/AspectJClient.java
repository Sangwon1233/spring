package com.sangwon97.aop.ex04;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.sangwon97.aop.ex02.adv.Seasoning;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AspectJClient {
    public static void main(String[] args) {
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* two*(..))");//..는 파라미터를 신경 안쓰겠다
        
        Advisor advisor = new DefaultPointcutAdvisor(pc , new Seasoning());

        ProxyFactory factory = new ProxyFactory(new First());
        factory.addAdvisor(advisor);
        ((First)factory.getProxy()).one();
        ((First)factory.getProxy()).two();
        ((First)factory.getProxy()).two2();
        log.info("++++++++++++++++");
        // ((FirstMatchingCompositeObservationHandler)factory.getProxy()).two();
        
        factory = new ProxyFactory(new Second());
        factory.addAdvisor(advisor);
        ((Second)factory.getProxy()).one();
        log.info("++++++++++++++++====================");
        ((Second)factory.getProxy()).two();





    }
    
}
