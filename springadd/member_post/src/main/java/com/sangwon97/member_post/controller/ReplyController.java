package com.sangwon97.member_post.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.sangwon97.member_post.dto.ReplyCri;
import com.sangwon97.member_post.service.ReplyService;
import com.sangwon97.member_post.vo.Member;
import com.sangwon97.member_post.vo.Reply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("reply")
@Log4j2
@AllArgsConstructor
public class ReplyController {
  private ReplyService service;
  // 목록조회
  @GetMapping({"list/{pno}" ,"list/{pno}/{lastRno}","list/{pno}/{lastRno}/{amount}"})
  public Map<?,?> list(@SessionAttribute(required = false, name = "member") Member member,
    @PathVariable("pno") Long pno,ReplyCri cri, @PathVariable(required = false , name = "lastRno")Optional<Long> lastRno,
    @PathVariable(required = false,name = "amount")Optional<Integer>amount){
      log.info(pno);
      // cri.setAmount(amount.orElse(10));//orElse는 널이면 안에 있는 값을 쓴다 isPresent()얘는 없으면 널 
      cri.setAmount(amount.orElseGet(()->10));
      cri.setLastRno(lastRno.orElse(0L));
      log.info(cri);
      return service.list(pno,cri,member);
  }
  
  // 단일조회
  // @GetMapping("{rno}")
  // public ResponseEntity<?>getMethdoName(@RequestBody Reply reply){
  //   service.write(reply);
  //   return service.findBy(rno);
  // }
  
  @Operation(summary="reply single select", description = "@PathVariable인 rno값을 입력 받고 해당 댓글을 json으로 리턴")
  @GetMapping("view/{pno}")
  public Reply view(@PathVariable Long pno){
    return service.findBy(pno);
  }

  // 등록
  @PostMapping
  @Operation(summary = "댓글 작성",description = "댓글 작성을 위해 필요한 값을 전달 받음. content writer,게시글 번호",
    responses = {
      @ApiResponse(responseCode = "200",description = "작성 성공",content = @Content(schema=@Schema(implementation = String.class))),
      @ApiResponse(responseCode = "500",description = "작성 실패")
    }
  )
    public ResponseEntity<?> write(@RequestBody Reply reply){
    log.info(reply);
    return service.write(reply) >0? ResponseEntity.ok().body("success") : ResponseEntity.internalServerError().build();//상태 값 관리
  }
  // 수정
  @PutMapping()
  public ResponseEntity<?> modify(@RequestBody Reply reply) {
    service.modify(reply);  
    return ResponseEntity.ok().body("success");
  }
  
  // 삭제
  @DeleteMapping("{rno}")
  public ResponseEntity<?> remove(@PathVariable Long rno){
    service.remove(rno);
    return ResponseEntity.ok().body("success");
  }
}
