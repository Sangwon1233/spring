package com.sangwon97.club.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sangwon97.club.entity.Member;
import com.sangwon97.club.entity.MemberRole;

@SpringBootTest
public class MemberRepositoryTests {

  @Autowired
  private MemberRepository repository;

  @Test
  public void testInsert(){

    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member member = Member.builder().email("user"+i +"@sangwon97 "+ i + ".com").name("USER" + i).password(encoder.encode("1234")).build();
      member.addMemberRole(MemberRole.USER);

      if(i > 80) {
        member.addMemberRole(MemberRole.MANAGER);
      }
      if(i > 90){
        member.addMemberRole(MemberRole.ADMIN);
      }

      repository.save(member);
    });
  }
  
}
