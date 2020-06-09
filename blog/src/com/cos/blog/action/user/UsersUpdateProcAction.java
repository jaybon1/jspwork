package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.SHA256;
import com.cos.blog.util.Script;

public class UsersUpdateProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. 유효성 검사
		
		HttpSession session = request.getSession();
		Users sessionUser = (Users) session.getAttribute("principal");
		
		if (sessionUser == null || !sessionUser.getUsername().equals(request.getParameter("username"))) {
			
			Script.getMessage("잘못된 접근입니다.", response);
			return;

		}
		
		
		if
		(
				request.getParameter("username").equals("")|| // 공백
				request.getParameter("username") == null||
				request.getParameter("password").equals("")||
				request.getParameter("password") == null||
				request.getParameter("email").equals("")||
				request.getParameter("email") == null ||
				request.getParameter("address").equals("")||
				request.getParameter("address") == null
				
		) {
			Script.getMessage("잘못된 접근입니다.", response);
			return; // 위의 사항 중 하나라도 해당되면 아예 실행이 안되게 설정
		}
		
		
		// 1. parameter 받기 (X-www.form-urlencoded 라는 MIME 타입 key=value)
		String username = request.getParameter("username");
		
		String rawPassword = request.getParameter("password");
		String password = SHA256.encodeSha256(rawPassword);
		
		String email = request.getParameter("email"); // e-mail 형식에 대한 유효성 검사도 시행해줘야함
		String address = request.getParameter("address");
		String userRole = RoleType.USER.toString(); // 마음대로 넣는 걸 방지하기 위해 enum으로 설정
		
		
		// 2. User 오브젝트 변환
		Users user = Users.builder()
					.id(sessionUser.getId())
					.password(password)
					.email(email)
					.address(address)
					.build();
		
		// 3. DB연결 - UserRepository의 save() 호출
		UsersRepository userRepository = UsersRepository.getInstance();
		int result = userRepository.update(user);
		
		
		// 4. index.jsp 페이지로 이동
		if(result == 1) {
//			response.sendRedirect("/blog/user?cmd=login"); //거의 안씀
//			RequestDispatcher dis = request.getRequestDispatcher("user/login.jsp");
//			dis.forward(request, response);
			
			Users user1 = userRepository.findByUsernameAndPassword(username, password);
			
			session.setAttribute("principal", user1); // 인증 주체
			
			Script.href("회원정보 수정에 성공하였습니다.", "/blog/board?cmd=home", response);
		}else {
			Script.back("회원정보 수정에 실패하였습니다.", response);
		}
		
	}

}
