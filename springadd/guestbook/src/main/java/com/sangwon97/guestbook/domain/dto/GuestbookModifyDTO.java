package com.sangwon97.guestbook.domain.dto;

import com.sangwon97.guestbook.domain.entity.GuestbookEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookModifyDTO {
    private Long gno;
    private String title;
    private String content;
    private String writer;

    public GuestbookEntity toEntity(){
        return new GuestbookEntity().builder().gno(gno).title(title).content(content).writer(writer).build();
    }
}
