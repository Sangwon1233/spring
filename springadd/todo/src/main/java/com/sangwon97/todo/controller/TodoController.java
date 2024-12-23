package com.sangwon97.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sangwon97.todo.dto.TodoWirteDto;
import com.sangwon97.todo.service.TodoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


// @Controller
// public class TodoController {
//   @GetMapping("todos")
//   public String list() {
//     int i3 = 10;
//       return "todo-list";
//   }
// }
@Controller
@AllArgsConstructor
@Log4j2
public class TodoController {
  private TodoService service;
  
  @GetMapping("todos")
  public String list(Model model) {
    model.addAttribute("todos", service.list());
      return "todo-list";
  }
  @PostMapping("todos")
  public String wirte(TodoWirteDto dto) {
      log.info(dto);
      service.wirte(dto);
      return "redirect:todos";
  }
  @RequestMapping("todos/remove")
  public String remove(Long id) {
      log.info(id);
      service.remove(id);
      return "redirect:/todos";
  }
  @RequestMapping("todos/modify")
  public String modify(Long id) {
      log.info(id);
      service.modify(id);
      
      return "redirect:/todos";
  }
  
}
