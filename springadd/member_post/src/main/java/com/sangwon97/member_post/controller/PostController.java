package com.sangwon97.member_post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sangwon97.member_post.dto.Criteria;

import com.sangwon97.member_post.service.PostService;
import com.sangwon97.member_post.vo.Post;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("post")
@AllArgsConstructor
@Log4j2
public class PostController {
  private PostService service;
  // private PostCategoryService CategoryService;
  
  @GetMapping("list")
  public void list(Criteria cri, Model model) {
    model.addAttribute("posts", service.list(cri));
    // model.addAttribute("pageDto", new PageDto(cri, service.count(cri))); // 복습 요망
        // model.addAttribute("pageDto", new PageDto(cri, service.count(cri))); // 복습 요망
  }

  @GetMapping("view")
    public void view(@ModelAttribute("cri") Criteria cri, @RequestParam("pno") Long pno, Model model) { // @ModelAttribute 어트리뷰트 옆의 괄호에다가 이름을 바인딩해줄 경우에 문제없어짐.
    model.addAttribute("post", service.view(pno));
  }
  @GetMapping("write")
  public void write(@ModelAttribute ("cri") Criteria cri){}

  @PostMapping("write")
  public String postWrite(Post post, Criteria cri){
    post.setCno(cri.getCategory());
    service.write(post);
      // return "list?";
     return "redirect:list?" + cri.getQs2();
  }
  
  


}
