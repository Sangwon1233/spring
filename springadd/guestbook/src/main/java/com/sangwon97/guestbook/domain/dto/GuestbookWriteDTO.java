package com.sangwon97.guestbook.domain.dto;

import com.sangwon97.guestbook.domain.entity.Guestbook;
import com.sangwon97.guestbook.domain.entity.GuestbookEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestbookWriteDTO {
    private String title;
    private String content;
    private String writer;

    // 생성자가 필요가 없음!
  // public GuestbookWriteDTO(GuestbookEntitiy entitiy){
  //   title = entitiy.getTitle();
  //   content = entitiy.getContent();
  //   writer = entitiy.getWriter();
  // }
  public Guestbook toEntity(){
    return new Guestbook().builder().title(title).content(content).writer(writer).build();
  }
   // 글쓰기에 있어서는 3가지만 필요하기 때문에 필요에 맞는 정확한 dto를 선언해서 활용해야 한다. 
}
