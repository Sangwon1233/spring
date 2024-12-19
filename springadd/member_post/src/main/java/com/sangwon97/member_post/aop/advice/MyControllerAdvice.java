package com.sangwon97.member_post.aop.advice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sangwon97.member_post.exception.NotMyPostException;

@ControllerAdvice
public class MyControllerAdvice {
  @ExceptionHandler({UnsupportedEncodingException.class, NotMyPostException.class})
  public String UnsignedAuthException(Exception ex) throws UnsupportedEncodingException{
    return "redirect:/msg?msg=" + URLEncoder.encode(ex.getMessage(), "utf-8")  + "&url=/member/signin";
  }
}
