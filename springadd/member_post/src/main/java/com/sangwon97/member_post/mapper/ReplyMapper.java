package com.sangwon97.member_post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sangwon97.member_post.dto.ReplyCri;
import com.sangwon97.member_post.vo.Reply;

@Mapper
public interface ReplyMapper {
//	int write(Reply reply); ? 답글?
	
	int insert(Reply reply); 
	int update(Reply reply);
	int delete(Long rno);
	int deleteAll(Long pno);
	
	Reply selectOne(Long rno);
	List<Reply> selectList(@Param("pno") Long pno, @Param("cri") ReplyCri cri); // 댓글은 포스트에 종속된 존재이기 때문임.
	List<Reply> selectMyList(String id);
	List<Reply> selectListByMe(Reply reply);
	
	
//	int increaseLikes(Long rno);
}
