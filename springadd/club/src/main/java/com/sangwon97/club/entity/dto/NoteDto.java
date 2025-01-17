package com.sangwon97.club.entity.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder.Default;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class NoteDto {
  private Long num;
  private String title;
  private String content;
  private String writerEmail;
  private Long mno;
  private LocalDateTime regDate, modDate;

  private long likeCnt;
  private long attachCnt;
  
  @Default
  private List<Attach> attachDTOs = new ArrayList<>();


}
