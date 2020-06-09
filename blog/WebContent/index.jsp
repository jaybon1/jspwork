<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<%
// 	response.sendRedirect("/blog/board?cmd=home");

%>

<c:redirect url="/board?cmd=home&page=0" /> <!-- url 찾아가는 방식이 JSP코드와 다르다. -->

