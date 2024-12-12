package com.sangwon97.jdbc.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sangwon97.jdbc.vo.Member;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberDaoTests {
  @Autowired
  private MemberDao dao;

  @Test
  public void testGetTime(){
    log.info(dao.getTime());
  }
  @Test
  public void testRegister(){
    Member member = Member.builder().id("abcd").pw("1234").name("스프링부트").build();
    log.info(member);
  }
}
