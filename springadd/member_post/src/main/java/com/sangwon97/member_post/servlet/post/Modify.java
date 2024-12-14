package com.sangwon97.member_post.servlet.post;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sangwon97.member_post.dto.Criteria;
import com.sangwon97.member_post.service.PostService;
import com.sangwon97.member_post.service.PostServiceImpl;
import com.sangwon97.member_post.utils.Commons;
import com.sangwon97.member_post.vo.Member;
import com.sangwon97.member_post.vo.Post;

@WebServlet("/post/modify")
public class Modify extends HttpServlet{
	private PostService service = new PostServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pnoStr = req.getParameter("pno");
		Object memberObj = req.getSession().getAttribute("member");
		Criteria criteria = new Criteria(req);
		String redirectURL = "list?" + criteria.getQs2();
		
		if(pnoStr == null || memberObj == null) {
			Commons.printMsg("SYSTEM :: ERR / INVALID APPROACH", redirectURL, resp);
			return;
		}
		
		Long pno = Long.valueOf(pnoStr);
		Member m = (Member)memberObj;
		if(!m.getId().equals(service.findBy(pno).getWriter())) {
			Commons.printMsg("SYSTEM :: ERR / POST CAN BE ONLY REMOVED BY WRITER OF IT\'S OWN", redirectURL, resp);
			return;
		}
		req.setAttribute("criteria", criteria);
		req.setAttribute("post", service.findBy(pno));
		req.getRequestDispatcher("/WEB-INF/jsp/post/modify.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PostService post = new PostServiceImpl();
		Object memberObj = req.getSession().getAttribute("member");
		Criteria criteria = new Criteria(req);
		
		if(memberObj == null) {
			Commons.printMsg("SYSTEM :: ERR / INVALID APPROACH", "list?" +criteria.getQs2(), resp);
			return;
		}
		Member m = (Member)memberObj;
		
		String title = req.getParameter("title");		
		String content = req.getParameter("content");		
		String pnoStr = req.getParameter("pno");		
		Long pno = Long.valueOf(pnoStr);
		
		if(!m.getId().equals(service.findBy(pno).getWriter())) {
			Commons.printMsg("SYSTEM :: ERR / POST CAN BE ONLY MODED BY WRITER OF IT'S OWN", "list?" +criteria.getQs2(), resp);
			return;
		}
		
		service.modify(Post.builder().title(title).content(content).pno(pno).build());
		resp.sendRedirect("view?pno="+pno+ "&" +criteria.getQs2());
	}
	
}