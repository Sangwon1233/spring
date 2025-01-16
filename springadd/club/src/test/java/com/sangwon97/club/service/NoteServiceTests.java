package com.sangwon97.club.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.sangwon97.club.entity.dto.NoteDto;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class NoteServiceTests {

  @Autowired
  private NoteService service;

  @Test
  public void testGet() {
    log.info(service.get(3L));
  }

  // Long write(NoteDTO dto);
  // Note get(Long num);
  // List<Note> listByEmail(String email);
  // List<Note> listByMno(Long mno);
  // void modify(NoteDTO dto);
  // void remove(Long num);

  @Test
  @Transactional
  @Rollback(false)
  public void testWrite() {
    NoteDto dto = NoteDto.builder()
        .title("제목")
        .content("제목")
        .writerEmail("user100@sangwon97.com")
        .mno(100L)
        .attachDTOs(null)
        .build();
    service.register(dto);
  }

  @Test
  @Transactional
  public void testListByEmail() {
    service.listByEmail("user100@sangwon97.com").forEach(log::info);
  }

  @Test
  @Transactional
  public void testListByMno() {
    service.listByMno(100L).forEach(log::info);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testRemove() {
    service.remove(1L);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testModify() {
    NoteDto dto = NoteDto.builder()
        .num(8L)
        .title("바뀐제목")
        .content("바뀐내용")
        .writerEmail(".com")
        .mno(100L)
        .build();

    service.modify(dto);

  }

  @Test
  public void testRead() {
    NoteDto dto = service.get(17L).get();
    dto.getAttachDTOs().forEach(log::info);
  }
}
