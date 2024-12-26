package com.sangwon97.guestbook.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sangwon97.guestbook.domain.dto.PageRequestDto;
import com.sangwon97.guestbook.service.GuestbookService;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Log4j2
@RequestMapping("guestbook")
public class GuestbookController {
    @Inject
    private GuestbookService service;

    @GetMapping({" ","/","list"})
    public String list(Model model, PageRequestDto dto) {
        log.info("list");
        model.addAttribute("result", service.list(dto));
       return "/guestbook/list"; 
    }
    
}

