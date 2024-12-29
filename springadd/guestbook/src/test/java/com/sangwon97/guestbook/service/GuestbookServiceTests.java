package com.sangwon97.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sangwon97.guestbook.domain.dto.GuestbookDto;
import com.sangwon97.guestbook.domain.dto.GuestbookListDTO;
import com.sangwon97.guestbook.domain.dto.PageRequestDto;
import com.sangwon97.guestbook.domain.dto.PageResultDto;
import com.sangwon97.guestbook.domain.entity.Guestbook;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class GuestbookServiceTests {
  @Autowired
  private GuestbookService service;

  @Test
  public void testWrite(){
    GuestbookDto dto = GuestbookDto.builder().title("연습").content("내용").writer("test").build();
    Long gno = service.writer(dto);
    assertNotNull(gno);
  }

  // @Test
  // public void testList(){
  //   // PageRequestDto dto = new PageRequestDto();
  //   PageRequestDto dto = PageRequestDto.builder().page(10).size(10).build();
  //   PageResultDto<GuestbookDto, Guestbook> pageResultDto = service.list(dto);
  //   log.info(pageResultDto);

  //   service.list(new PageRequestDto(10, 10)).getDtoList().forEach(log::info);

  // }

  // @Test
  // public void testListPage(){
  //   PageResultDto<GuestbookDto,Guestbook> dto = service.list(new PageRequestDto(2,10));
  //   log.info(dto);
  //   dto.getPageList().forEach(log::info);
  // }
}