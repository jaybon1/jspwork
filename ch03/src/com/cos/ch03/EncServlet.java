package com.cos.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * 
 * 한글 테스트 데이터 전달
 *
 */

@WebServlet("/postman")
public class EncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EncServlet() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("doPost");
//		
//		BufferedReader br = request.getReader();
//		
//		String temp = null;
//		StringBuilder sb = new StringBuilder();
//		
//		while ((temp = br.readLine()) != null) {
//			System.out.println(temp);
//			sb.append(temp);
//		}
//		
//		Gson gson = new Gson();
//		User user = gson.fromJson(sb.toString(), User.class);
//		// DB에 인서트
//		
//		System.out.println(user.getUsername());
//		System.out.println(user.getPassword());
//		System.out.println(user.getEmail());
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPut"); // put과 delete요청은 x-www-formurlencoded 파싱이 안됨

//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
//		String email = req.getParameter("email");
//		
//		User user = new User();
//		user.setUsername(username);
//		user.setPassword(password);
//		user.setEmail(email);
//		
//		System.out.println(user.getUsername());
//		System.out.println(user.getPassword());
//		System.out.println(user.getEmail());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doDelete");
	}
}

