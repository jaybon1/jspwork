<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>
<%@include file="../include/authentication.jsp"%>

<div class="container">
	<!-- 컨테이너에 담으면 양쪽 끝을 띄워준다 -->
	<form action="/blog/board?cmd=writeProc" method="post" onsubmit="return validate()">

		<div class="form-group">
			<label for="title">Title:</label> 
			<input type="text" class="form-control" placeholder="title" id="title" name="title">
		</div>

		<div class="form-group">
			<label for="content">Content:</label>
			<textarea class="form-control" rows="5" placeholder="내용을 입력하세요" id="content" name="content"></textarea>
		</div>

		<button type="submit" class="btn btn-primary">글쓰기</button>

	</form>
</div>

<script>

	function validate() {
        
        var test = $("#title").val();
        var test1 = $("#content").text();
		
		if (test == null || test.length == 0) {
			alert("제목을 입력해주세요.");
			return false;
		} else if (test1 == null || test1.length == 0) {
			alert("내용을 입력해주세요.");
			return false;
		}
		return true;
	}
	
</script>

<%@include file="../include/footer.jsp"%>


