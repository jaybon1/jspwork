package com.cos.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.naver.NaverCallbackAction;
import com.cos.blog.action.naver.NaverJoinProcAction;

// http://localhost:8000/blog/board
@WebServlet("/oauth/naver")
public class NaverController extends HttpServlet {
	private final static String TAG = "NaverController : ";
	private static final long serialVersionUID = 1L;
       
    public NaverController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8000/blog/user?cmd=join
		String cmd = request.getParameter("cmd");
		System.out.println(TAG+"router : "+cmd);
		Action action = router(cmd);
		action.execute(request, response);
	}
	
	public Action router(String cmd) {
		
		if(cmd.equals("callback")) {
			return new NaverCallbackAction();
		} else if(cmd.equals("joinProc")) {
			return new NaverJoinProcAction();
		}
		
		return null;
		
	}
	
}
