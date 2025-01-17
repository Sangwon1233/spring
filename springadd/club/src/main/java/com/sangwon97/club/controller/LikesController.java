package com.sangwon97.club.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sangwon97.club.service.LikesService;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Log4j2
@RequestMapping("api/v1/likes")
public class LikesController {
  
  @Autowired
  private LikesService service;

  @GetMapping
  public boolean get(LikesDto dto) {
    log.info(dto);  
    return service.get(dto);

  }
  

  // @PreAuthorize("email -- dto.email")
  @PostMapping("{num}")
  public void postMethodName(@RequestBody LikesDto dto,@AuthenticationPrincipal String eamil){
    log.info(writerEmail);
    log.info(dto);
    service.toggle(dto);
    return ResponseEntity.ok().body(Map.of("result",service.toggle(dto)));
  }
}
