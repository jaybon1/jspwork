package com.cos.blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

// join 실행파일
public class UsersJoinProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 0. 유효성 검사
		if
		(
				request.getParameter("username").equals("")|| // 공백
				request.getParameter("username") == null|| // 값이 X
				request.getParameter("password").equals("")||
				request.getParameter("password") == null||
				request.getParameter("email").equals("")||
				request.getParameter("email") == null ||
				request.getParameter("address").equals("")||
				request.getParameter("address") == null
				
		) {
			return; // 위의 사항 중 하나라도 해당되면 아예 실행이 안되게 설정
		}
		
		// 1. parameter 받기 (X-www.form-urlencoded 라는 MIME 타입 key=value)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email"); // e-mail 형식에 대한 유효성 검사도 시행해줘야함
		String address = request.getParameter("address");
		String userRole = RoleType.USER.toString(); // 마음대로 넣는 걸 방지하기 위해 enum으로 설정
		
		// 2. User 오브젝트 변환
		Users user = Users.builder()
					.username(username)
					.password(password)
					.email(email)
					.address(address)
					.userRole(userRole)
					.build();
		
		// 3. DB연결 - UserRepository의 save() 호출
		UsersRepository userRepository = UsersRepository.getInstance();
		int result = userRepository.save(user);
		

		
		// 4. index.jsp 페이지로 이동
		if(result == 1) {
//			response.sendRedirect("/blog/user?cmd=login"); //거의 안씀
//			RequestDispatcher dis = request.getRequestDispatcher("user/login.jsp");
//			dis.forward(request, response);
			Script.href("회원가입에 성공하였습니다.", "/blog/user?cmd=login", response);
		}else {
			Script.back("회원가입에 실패하였습니다.", response);
		}
		
	}
}
