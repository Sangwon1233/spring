package com.sangwon97.member_post.service;

import java.util.List;

import com.sangwon97.member_post.dto.Criteria;
import com.sangwon97.member_post.vo.Post;

public interface PostService {
	
	int write(Post post);
	
	int modify(Post post);
	
	int remove(Long pno);
	
	Post findBy(Long pno);
	
	Post view(Long pno);

	List<Post> list(Criteria cri);
	
	int count(Criteria cri);

}