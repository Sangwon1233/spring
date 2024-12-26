package com.sangwon97.guestbook.domain.dto;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestbookDto {
  private Long gno;
  private String title,content,writer;
  private LocalDateTime regDate ,modDate;
  
  // public GuestbookDto(GuestbookEntity entity){
  //   this.gno = entity.getGno();
  //   this.title = entity.getTitle();
  //   this.writer = entity.getWriter();
  //   this.regDate = entity.getRegDate();
  //   this.modDate = entity.getModDate();

   
    
}


