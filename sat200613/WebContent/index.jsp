<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- HTML 방식 -->
<!-- <a href="/sat200613/home.jsp?name=sungsu">aa</a> -->


<!-- 자바스크립트 방식 -->
<script>
	location.href = "/sat200613/home.jsp?name=sungsu";
</script>


<!-- 리다이렉트 방법 -->
<%

// 	response.sendRedirect("/sat200613/home.jsp?name=sungsu");

%>


<!-- 디스패쳐 방법 -->
<%

// 	RequestDispatcher rd = request.getRequestDispatcher("/home.jsp?name=sungsu");
// 	rd.forward(request, response);

%>


<!-- JSTL 방법 -->
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <c:redirect url="/home.jsp?name=sungsu" /> url 찾아가는 방식이 JSP코드와 다르다. --%>