package com.love.blog.action.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.love.blog.action.Action;

public class UsersJoinAction implements Action  {

	// 컨트롤러가 해야될 일들을 위임해서 다른 클래스가 일을 하게 하는 것을 팩토리패턴이라고 한다
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("user/join.jsp");
		dis.forward(request, response);
	}
}
