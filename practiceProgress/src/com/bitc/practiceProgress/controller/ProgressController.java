package com.bitc.practiceProgress.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.action.progress.ProgressHomeAction;
import com.bitc.practiceProgress.action.progress.ProgressInputAction;

// http://localhost:8000/blog/user
@WebServlet("/progress")
public class ProgressController extends HttpServlet {
	private final static String TAG = "ProgressController : ";
	private static final long serialVersionUID = 1L;

	public ProgressController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8000/blog/user?cmd=join
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "router : " + cmd);
		Action action = router(cmd);
		action.execute(request, response);
	}

	public Action router(String cmd) {
		if (cmd.equals("home")) {
			// 회원가입 페이지로 이동
			return new ProgressHomeAction();
		} else if (cmd.equals("input")) {
			// 회원가입 페이지로 이동
			return new ProgressInputAction();
		}

		return null;
	}

}