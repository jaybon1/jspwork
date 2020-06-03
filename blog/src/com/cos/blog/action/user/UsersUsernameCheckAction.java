package com.cos.blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;

public class UsersUsernameCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		
		PrintWriter out = response.getWriter();
		
		if(username.equals("")) {
			out.print(2);
			return;
		}
		
		UsersRepository userRepository = UsersRepository.getInstance();
		int temp = userRepository.findByUsername(username);
		
		out.print(temp); // println을 쓰면 엔터키가 들어가기 때문에 주의
		
		// ajax 로 출력
		
	}
	
}

