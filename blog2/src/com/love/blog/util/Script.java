package com.love.blog.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	
	public static void back(String msg, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8"); // 받는 쪽에서 해석하도록
			PrintWriter out = response.getWriter();
			out.println(
					"<script>\r\n" + 
							"alert(\"" + msg + "\");\r\n" + 
							"history.back();\r\n" + 
					"</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}