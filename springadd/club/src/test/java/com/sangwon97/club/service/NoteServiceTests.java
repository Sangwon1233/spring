package com.sangwon97.club.service;

import java.io.Writer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.sangwon97.club.entity.dto.NoteDto;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Transactional
@Log4j2
public class NoteServiceTests {
  @Autowired
  private NoteService service;

  @Test
  @Rollback(false)
  public void testWrite(){
    service.register(NoteDto.builder().title("testTitle").content("content").writerEmail("null").build());

  }
  @Test
  public void testRead(){


  }

}
