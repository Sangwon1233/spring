package com.sangwon97.member_post.dto;

import lombok.Data;

@Data
public class PageDto {
	
	private Criteria cri = new Criteria();
	private int total = 123; // 게시글 총량
	private int startPage; // 페이지 버튼 시작 번
	private int endPage; // 페이지 버튼 종료 번호
	private int HiddenPage;
	private int showNum;
	private int pageCount; // 페이지 버튼 갯수
	
	// 이전 이후 계산용 필드
	private boolean prev; 
	private boolean next;
	private boolean doublePrev;
	private boolean doubleNext;
	
	
	public PageDto(int total) {
		this(new Criteria(), total);
	}
	
	public PageDto(Criteria criteria, int total) {
		this(criteria, total, 10);
	}
	
	public PageDto(Criteria criteria, int total, int pageCount) {
		this.cri = criteria;
		this.total = total;
		this.pageCount = pageCount;
		
		endPage = (criteria.getPage() + pageCount - 1) / pageCount * pageCount;
		startPage = endPage - pageCount + 1;

		this.HiddenPage = endPage - pageCount; 
		
		int realEnd = (total +criteria.getAmount()-1) / criteria.getAmount(); 
		
		if(realEnd < endPage) {
			endPage = realEnd;
		}
		
		prev = criteria.getPage() > 1 ;
		next = criteria.getPage() < realEnd;
		doublePrev = startPage > 1;
		doubleNext = endPage < realEnd;
	}
	
	public static void main(String[] args) {
		PageDto dto = new PageDto(255);
		System.out.println(dto);
	}

	
//	public int pageOffset() {
//		int next = criteria.getPage();
//		boolean flag = true;
//		// 페이지 수
//		
//		while(flag) {
//			
//		}
//		// next * amount - amount
//		
//		return next;
//	}
	
	
}