package com.sangwon97.member_post.service;

import java.util.List;

// import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sangwon97.member_post.dto.Criteria;
import com.sangwon97.member_post.mapper.AttachMapper;
import com.sangwon97.member_post.mapper.PostMapper;
import com.sangwon97.member_post.mapper.ReplyMapper;
// import com.kcanmin.member_post.utils.MybatisInIt;
import com.sangwon97.member_post.vo.Post;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PostServiceImpl implements PostService{

	private PostMapper postMapper;
	private AttachMapper attachMapper;
	private ReplyMapper replyMapper;

	
	@Override
	public int write(Post post){
			postMapper.insert(post);
			post.getAttachs().forEach(a->{
				a.setPno(post.getPno());
				attachMapper.insert(a);
			});
			return 0;
		}
	
	@Override
	public int modify(Post post) {
		return postMapper.update(post);
	}
	
	@Override
	public int remove(Long pno) {
		attachMapper.delete(pno);
		replyMapper.deleteAll(pno);
		return postMapper.delete(pno);
	}

	@Override
	public Post findBy(Long pno) {
		return postMapper.selectOne(pno);
	}

	@Override
	public List<Post> list(Criteria cri){
		return postMapper.selectList(cri);
	}
	
	@Override
	public int count(Criteria cri) {
		return postMapper.getCount(cri);
	}
	
	@Override
	public Post view(Long pno) {
		postMapper.increaseViewCount(pno);
		Post post = postMapper.selectOne(pno);
		post.setAttachs(attachMapper.selectList(pno));
		return postMapper.selectOne(pno);
	}
}