package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.SHA256;
import com.cos.blog.util.Script;

public class UsersLoginProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher dis = request.getRequestDispatcher("user/join.jsp");
//		dis.forward(request, response);

		if
		(
				request.getParameter("username").equals("")|| // 공백
				request.getParameter("username") == null|| // 값이 X
				request.getParameter("password").equals("")||
				request.getParameter("password") == null
		) {
			return; // 위의 사항 중 하나라도 해당되면 아예 실행이 안되게 설정
		}
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String password = SHA256.encodeSha256(rawPassword);
		
		UsersRepository userRepository = UsersRepository.getInstance();
		Users user = userRepository.findByUsernameAndPassword(username, password);
		
		if(user != null) {
			//로그인 성공 (세션은 request가 들고 있음)
			HttpSession session = request.getSession();
			session.setAttribute("principal", user); // 인증 주체
			
			if(request.getParameter("remember") != null) { // null이 아니면 무조건 체크박스를 체크했다는 것!
				
//				Cookie cookie = new Cookie("remember", user.getUsername());
//				response.addCookie(cookie);
				
				response.setHeader("Set-Cookie", "remember=" + user.getUsername());
				
			} else {
				Cookie cookie = new Cookie("remember", null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
			Script.href("로그인 성공", "/blog/index.jsp",response);
			
		}else {
			Script.back("로그인 실패", response);
		}

	}

}










