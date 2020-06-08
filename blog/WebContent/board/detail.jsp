<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>

<div class="container">

	<button type="button" class="btn btn-light" onclick="history.back()">뒤로가기</button>

	<c:if test="${sessionScope.principal.id == dto.board.userId}">

		<a href="/blog/board?cmd=update&id=${dto.board.id}" class="btn btn-warning">수정</a> <!-- 하이퍼링크는 get방식만 사용 -->
		<button type="button" class="btn btn-danger" onclick="deleteById(${dto.board.id})">삭제</button>

	</c:if>


	<br /> <br />


	<h6>
		작성자 : <i>${dto.username}</i>
	</h6>

	<br />

	<h3>${dto.board.title}</h3>

	<div class="container p-3 my-3 border">${dto.board.content}</div>

</div>

<script src="/blog/js/detail.js"></script>

<%@include file="../include/footer.jsp"%>


