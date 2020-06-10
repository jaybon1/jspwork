<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="include/nav.jsp"%>

<div class="container">


	<div class="row col-md-12 m-2" style="height: 70px">
		<form class="form-inline col-md-12 justify-content-center" action="/blog/board?cmd=search">
			<input type="hidden" name="cmd" value="search" /> <input type="hidden" name="page" value="0" /> <input type="text" name="keyword"
				class="form-control mr-sm-2" placeholder="Search">
			<button class="btn btn-success" type="submit">검색</button>
		</form>
	</div>


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

	<c:choose>
		<c:when test="${empty param.keyword}">
			<c:set var="pagePrevious" value="/blog/board?cmd=home&page=${param.page - 1}" />
		</c:when>
		<c:otherwise>
			<c:set var="pagePrevious" value="/blog/board?cmd=search&page=${param.page - 1}&keyword=${param.keyword}" />
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty param.keyword}">
			<c:set var="pageNext" value="/blog/board?cmd=home&page=${param.page + 1}" /> 
		</c:when>
		<c:otherwise>
			<c:set var="pageNext" value="/blog/board?cmd=search&page=${param.page + 1}&keyword=${param.keyword}" />
		</c:otherwise>
	</c:choose>

	<br />

	<!-- disabled -->
	<ul class="pagination justify-content-center">

		<c:choose>
			<c:when test="${lastPage == param.page}">
				<li class="page-item"><a class="page-link" href="${pageScope.pagePrevious}">Previous</a></li>
				<li class="page-item disabled"><a class="page-link" href="${pageScope.pageNext}">Next</a></li>
			</c:when>

			<c:when test="${param.page == 0}">
				<li class="page-item disabled"><a class="page-link" href="${pageScope.pagePrevious}">Previous</a></li>
				<li class="page-item"><a class="page-link" href="${pageScope.pageNext}">Next</a></li>
			</c:when>

			<%-- 			<c:when test="${boards.size() < 3}"> --%>

			<c:otherwise>
				<li class="page-item"><a class="page-link" href="${pageScope.pagePrevious}">Previous</a></li>
				<li class="page-item"><a class="page-link" href="${pageScope.pageNext}">Next</a></li>
			</c:otherwise>

		</c:choose>
	</ul>

</div>

<%@include file="include/footer.jsp"%>



