<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>



<div class="container">
	<div class="d-flex justify-content-center">
		<form action="/blog/user?cmd=profileUploadProc" method="post" enctype="multipart/form-data">

			<div class="form-group">
			
				<img id="img__wrap" onerror="this.src='/blog/image/userProfile.png'" 
				src="${sessionScope.principal.userProfile}" width="350px" height="300px" />
				
			</div>
			<div class="form-group">
			
				<input type="file" name="userProfile" id="img__preview" />
				<!-- 단어를 이을 때 자바는 카멜 , html은 언더바를 주로 사용한다, 아이디쓸 때 언더바 두개 쓰기 -->
				
			</div>
				<input type="hidden" name="id" value="${sessionScope.principal.id}">
			<div class="form-group">
			
				<button class="btn btn-primary w-100">사진전송</button>
			
			</div>

		</form>
	</div>
</div>


<script src="/blog/js/imgPreview.js"></script>

<%@include file="../include/footer.jsp"%>