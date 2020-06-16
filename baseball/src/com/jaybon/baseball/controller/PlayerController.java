package com.jaybon.baseball.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jaybon.baseball.action.Action;
import com.jaybon.baseball.action.player.PlayerGetDescAction;
import com.jaybon.baseball.action.player.PlayerGetPlayerAction;



// http://localhost:8000/blog/user
@WebServlet("/player")
public class PlayerController extends HttpServlet {
	private final static String TAG = "PlayerController : ";
	private static final long serialVersionUID = 1L;

	public PlayerController() {
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
		// http://localhost:8000/product/user?cmd=test
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "router : " + cmd);
		Action action = router(cmd);
		action.execute(request, response);
	}

	public Action router(String cmd) {
		if (cmd.equals("getPlayer")) {
			return new PlayerGetPlayerAction();
		} else if (cmd.equals("getDesc")) {
			return new PlayerGetDescAction();
		} 
		return null;
	}
}