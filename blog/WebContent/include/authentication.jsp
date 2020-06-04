<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 이 페이지로의 잘못된 접근을 막으려면 이 페이지에서 막거나 web.xml에서 막아야한다  -->
<c:if test="${empty sessionScope.principal}">
	<c:redirect url="/index.jsp"></c:redirect>
</c:if>