<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 자바스크립트 방식 으로 name 파라미터 출력 -->
	<!-- window.location.search를 split을 이용하여  sungsu만 나오게 해보세요 -->
	<script>
		alert(window.location.search);
	</script>

	<h1>페이지 이동 성공</h1>

	<!-- JSP방식 으로 name 파라미터 출력 -->
	<%
		out.print(request.getParameter("name"));
	%>
	
	<br/>
	<br/>

	<!-- HTML + EL방식 으로 name 파라미터 출력 -->
	<p>${param.name}</p>
	
	<br/>

	<!-- JSTL + EL방식 으로 name 파라미터 출력 -->
	<c:out value="${param.name}"></c:out><br/>
	
	<br/>
	<br/>
	
	<a href="mvc1.jsp">mvc1로 이동</a>
</body>
</html>