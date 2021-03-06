package com.love.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.love.blog.action.Action;
import com.love.blog.action.users.UsersJoinAction;
import com.love.blog.action.users.UsersJoinProcAction;


@WebServlet("/UsersController")
public class UsersController extends HttpServlet {
	
	private static final String TAG = "UsersController : ";
	
	private static final long serialVersionUID = 1L;

    public UsersController() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd"); 
		
		System.out.println(TAG + "doProcess : " + cmd);
		

		Action action = router(cmd);
		action.execute(request, response);

	}

	public Action router(String cmd) {

		if (cmd.equals("join")) {
			// 회원가입 페이지로 이동
			// http://localhost:8000/blog/user?cmd=join
			// 컨트롤러가 해야될 일들을 위임해서 다른 클래스(UsersJoinAction)가 일을 하게 하는 것을 !팩토리패턴!이라고 한다
			return new UsersJoinAction();
		} else if (cmd.equals("joinProc")) {
			// 회원가입을 진행 한 후 -> index.jsp로 이동
			return new UsersJoinProcAction();
		} else if (cmd.equals("update")) {
			// 회원 수정 페이지로 이동 (세션에 User 오브젝트를 가지고 있을 예정)
		} else if (cmd.equals("updateProc")) {
			// 회원 수정을 진행 한 후 -> index.jsp로 이동
		} else if (cmd.equals("delete")) {
			// 회원 수정페이지에서 삭제할 예정 - 회원 삭제를 진행 한 후 -> 로그아웃을 하고 -> index.jsp로 이동
		} else if (cmd.equals("login")) {
			// 회원 로그인 페이지로 이동
		} else if (cmd.equals("loginProc")) {
			// 회원 로그인을 수행한 후 -> 세션에 등록을 하고 -> index.jsp로 이동
		}
		return null;
	}
}
