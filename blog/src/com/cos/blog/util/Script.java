package com.cos.blog.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	public static void back(String msg, HttpServletResponse response) { // 뒤로가기
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charser=utf-8");
			out.println("<script>");
			out.println("alert('" + msg + "')");
			out.println("history.back()");
			out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void href(String msg, String uri, HttpServletResponse response) { // 뒤로가기
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charser=utf-8");
			out.println("<script>");
			out.println("alert('" + msg + "')");
			out.println("location.href='"+uri+"';");
			out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static void href(String uri, HttpServletResponse response) { // 뒤로가기
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charser=utf-8");
			out.println("<script>");
			out.println("location.href='"+uri+"';");
			out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
