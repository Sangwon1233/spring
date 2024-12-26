package com.sangwon97.guestbook.domain.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "dtoList")
public class PageResultDto<D,E> {
  private List<D> dtoList;

  private int totalPage;
  private int page,size;
  private int start,end;
  private boolean prev,next;

  private List<Integer> pageList;

  //Entity를가져와서 Dto으로 반환
  public PageResultDto(Page<E> result,Function<E,D>fn){
    dtoList = result.stream().map(fn).toList(); //map에 의해 tolist의 제네릭타입은 dto의 D타입
    totalPage = result.getTotalPages();

    Pageable Pageable = result.getPageable();
    page = Pageable.getPageNumber() +1;
    size = Pageable.getPageSize();

    int temEnd = (int)(Math.ceil(page/10.0)*10);
    start = temEnd-9;
    prev = start>1;
    
    end = totalPage>temEnd ? temEnd:totalPage;
    next = totalPage>temEnd;

    pageList = IntStream.rangeClosed(start, end).boxed().toList(); //인트스트림이 박스(맵핑)처리하면 스트링 스트림이 됨
  }


}
