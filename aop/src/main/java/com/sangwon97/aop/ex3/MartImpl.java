package com.sangwon97.aop.ex3;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MartImpl implements Mart{

    @Override
    public void getProduct(String name) {
        log.info("상품명 ::" + name);
        throw new RuntimeException("예외 발생");
    }
}
