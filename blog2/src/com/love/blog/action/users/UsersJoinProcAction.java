package com.love.blog.action.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.love.blog.action.Action;
import com.love.blog.model.RoleType;
import com.love.blog.model.Users;
import com.love.blog.repository.UsersRepository;
import com.love.blog.util.Script;

public class UsersJoinProcAction implements Action  {
	
	// 컨트롤러가 해야될 일들을 위임해서 다른 클래스가 일을 하게 하는 것을 팩토리패턴이라고 한다
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. // 페이지 오류를 막기위해(공격 등) 유효성 검사부터 해야한다
		if(request.getParameter("username").equals("") ||
				request.getParameter("username") == null ||
				request.getParameter("password").equals("") ||
				request.getParameter("password") == null ||
				request.getParameter("email").equals("") ||
				request.getParameter("email") == null ||
				request.getParameter("address").equals("") ||
				request.getParameter("address") == null) {
			
			// 로그하나 남겨야함
			return;
		}
		
		// 1. 파라메터 받기 (x-www-form-urlencoded)라는 MIME타입 key=value
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email"); // postman등으로 공격하면 형식에 맞지않게 보낼수 있기 때문에 유효성체크 해줘야한다(지금은 안함)
		String address = request.getParameter("address");
		String userRole = RoleType.USER.toString();
		
		
		// 2. User 오브젝트 변환
		Users user = Users.builder()
				.username(username)
				.password(password)
				.email(email)
				.address(address)
				.userRole(userRole)
				.build();
		
		
		// 3. DB 연결 - UserRepository의 save() 호출
		UsersRepository usersRepository = UsersRepository.getInstance();
		int result = usersRepository.save(user);
		
		// 4. index.jsp 페이지로 이동
		if(result == 1) {
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		} else {
			Script.back("회원가입에 실패하였습니다.", response);
		}
			
	}
}