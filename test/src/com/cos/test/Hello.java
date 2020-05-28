package com.cos.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/hello" })
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**  
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request Get");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"    <title>Document</title>\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"    <h1>fruits</h1>\r\n" + 
				"\r\n" + 
				"    <table border=\"1\">\r\n" + 
				"        <tr height=\"100\">\r\n" + 
				"            <td width=\"100\">ttalgi</td>\r\n" + 
				"            <td width=\"100\">subak</td>\r\n" + 
				"            <td width=\"100\">banana</td>\r\n" + 
				"            <td width=\"100\">charmoe</td>\r\n" + 
				"            <td width=\"100\">kiwi</td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr height=\"100\">\r\n" + 
				"            <td>pineapple</td>\r\n" + 
				"            <td>grape</td>\r\n" + 
				"            <td>pear</td>\r\n" + 
				"            <td>jadu</td>\r\n" + 
				"            <td>apple</td>\r\n" + 
				"        </tr>\r\n" + 
				"\r\n" + 
				"    </table>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request Post");
	}
}
