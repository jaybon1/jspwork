package com.cos.blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

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
		Users user = userRepository.findByUsername(username);
		
		int result = -1;
		if(user != null && user.getId() == 0) {
			result = 0;
		}else if(user.getId() != 0) {
			result = 1;
		}
		
		Script.outText(result + "", response);
		
	}
}

