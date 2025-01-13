package com.sangwon97.club.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.sangwon97.club.entity.Member;
import com.sangwon97.club.entity.MemberRole;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MemberRepositoryTests {

  @Autowired
  private MemberRepository repository;

  @Autowired
  private PasswordEncoder encoder;

  @Test
  @Rollback(false)
  @Transactional
  public void testInsert() {

    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member m = Member.builder()
      .email("user" + i + "@sangwon.com")
      .name("user" + i)
      .password(encoder.encode("1234"))
      .build();

      m.addMemberRole(MemberRole.USER);

      if(i > 80) {
        m.addMemberRole(MemberRole.MANAGER);
      }
      if(i > 90) {
        m.addMemberRole(MemberRole.ADMIN);
      }

      repository.save(m);
    });
  }
  
}
