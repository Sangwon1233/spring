package com.sangwon97.guestbook.controller;

import java.time.LocalDateTime;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sangwon97.guestbook.domain.SampleDto;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
@RequestMapping("sample")
public class SampleController {
  @GetMapping({"ex02", "exLink"})
  public void ex02(Model model){//void라 sample폴더가 필요함
    model.addAttribute("list",
    LongStream.rangeClosed(1, 20)
    .mapToObj(i->SampleDto.builder()
      .sno(i)
      .first("first"+i)
      .last("last"+i)
      .regTime(LocalDateTime.now())
    .build())); //Long 타입 i를 SampleDto로
  }

  @GetMapping("exInline")
  public String exInline(RedirectAttributes rttr) {
    SampleDto dto = SampleDto.builder()
    .sno(100L)
    .first("first")
    .last("last")
    .regTime(LocalDateTime.now())
  .build();
  rttr.addFlashAttribute("dto", dto);
    rttr.addFlashAttribute("result", "success");
    return "redirect:ex03"; 
  }

  @GetMapping("ex03")
  public void ex03(){
    log.info("ex03");
  } 
}


