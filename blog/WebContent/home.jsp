<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="include/nav.jsp"%>

<div class="container">

	<c:forEach var="board" items="${boards}">

		<div class="card m-2" style="width: 100%">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<p class="card-text">${board.content}</p>
				<a href="/blog/board?cmd=detail&page=${param.page}&id=${board.id}" class="btn btn-primary">상세보기</a>
				<p class="card-text">조회수 : ${board.readCount}</p>
			</div>
		</div>

	</c:forEach>

	<br />

	<!-- disabled -->
	<ul class="pagination justify-content-center">

		<c:choose>
		
			<c:when test="${param.page == 0}">
				<li class="page-item disabled"><a class="page-link" href="/blog/board?cmd=home&page=${param.page - 1}">Previous</a></li>
				<li class="page-item"><a class="page-link" href="/blog/board?cmd=home&page=${param.page + 1}">Next</a></li>
			</c:when>
			
			<c:when test="${boards.size() < 3}">
				<li class="page-item"><a class="page-link" href="/blog/board?cmd=home&page=${param.page - 1}">Previous</a></li>
				<li class="page-item disabled"><a class="page-link" href="/blog/board?cmd=home&page=${param.page + 1}">Next</a></li>
			</c:when>
			
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="/blog/board?cmd=home&page=${param.page - 1}">Previous</a></li>
				<li class="page-item"><a class="page-link" href="/blog/board?cmd=home&page=${param.page + 1}">Next</a></li>
			</c:otherwise>
			
		</c:choose>
	</ul>

</div>

<%@include file="include/footer.jsp"%>



