package com.sangwon97.guestbook.domain.dto;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageResultDto<D,E> {
  private List<D> dtoList;
  //Entity를가져와서 Dto으로 반환
  public PageResultDto(Page<E> result,Function<E,D>fn){
    dtoList = result.stream().map(fn).toList();
  }


}
