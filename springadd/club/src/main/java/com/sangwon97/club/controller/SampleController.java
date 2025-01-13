package com.sangwon97.club.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sangwon97.club.security.dto.AuthMemberDTO;

import lombok.extern.log4j.Log4j2;




@Controller
@Log4j2
@RequestMapping("sample")
public class SampleController {

  @GetMapping("/all")
  public void all(){
    log.info("/all Start ::::::::::::::::::::::::::::::");
  }

  @GetMapping("/member")
  public void exMember(){
    log.info("/member Start ::::::::::::::::::::::::::::::");
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")//필수로 문자열을 넣어야함
  public void exAdmin(){
    log.info("/Admin Start ::::::::::::::::::::::::::::::");
  }

  @GetMapping("api")
  @PreAuthorize("isAuthenticated()")
  // @PreAuthorize("isAnonymous()")
  @ResponseBody
  public AuthMemberDTO getMethodName(@AuthenticationPrincipal AuthMemberDTO dto){
    return dto;
  }
  @GetMapping("exMemberOnly")
  @ResponseBody
  @PreAuthorize("#dto != null && #dto.username ep \"user1000sangwon97.com\"")
  public String exMemberOnly(@AuthenticationPrincipal AuthMemberDTO dto) {
      return dto.getEmail();
  }
  

}