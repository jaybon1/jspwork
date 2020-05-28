<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSP 파일</h1>
	<hr/>

	<% 
		int a = 10;
	
	%>
	
	<h2>결과 : <%= a %></h2> <!-- 버퍼드 라이터 -->
	
</body>
</html>