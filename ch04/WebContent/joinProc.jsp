<%@page import="java.sql.ResultSet"%>
<%@page import="ch04.Users"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	
	// DB연결
	final String SQL = "INSERT INTO users(id, username, password, email) VALUES(test1.nextval,?,?,?)";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "cos", "bitc5600");
	// DB에 insert
	PreparedStatement pstmt = conn.prepareStatement(SQL);
	pstmt.setString(1, username);
	pstmt.setString(2, password);
	pstmt.setString(3, email);
	int result = pstmt.executeUpdate();
	// 회원가입 완료 페이지 이동
	if(result == 1){
		ResultSet rs;
		String SQL1 ="select id from users where username like ?";
		pstmt = conn.prepareStatement(SQL1);
		pstmt.setString(1, username);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			int id = rs.getInt(1);
			Users user = new Users(id, username, password, email);
			request.setAttribute("user", user);
			RequestDispatcher dis = request.getRequestDispatcher("joinComplete.jsp");
			dis.forward(request, response);
		} else {
			System.out.println("아이디 검색에 실패했습니다");
		}


	//response.sendRedirect("joinComplete.jsp");
	}else {
		response.sendRedirect("joinError.jsp");
	}
%>