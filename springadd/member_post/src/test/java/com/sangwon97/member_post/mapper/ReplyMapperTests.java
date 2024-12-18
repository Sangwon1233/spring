package com.sangwon97.member_post.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sangwon97.member_post.dto.ReplyCri;

@SpringBootTest
public class ReplyMapperTests {
  @Autowired
  private ReplyMapper mapper;

  @Test
  public void testList() {
    mapper.selectList(1074L, new ReplyCri());
  }
}
