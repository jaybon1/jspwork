<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>

<script>
	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/blog/juso/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
		//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}

	function jusoCallBack(roadFullAddr) {
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.

		// 		document.getElementById("address").value = roadFullAddr; -> 잘됨

		// 		document.myForm.address.value = roadFullAddr; -> 잘됨

		var temp = document.querySelector("#address");
		temp.value = roadFullAddr;

	}
</script>


<script>
	var checkedUsername = false;

	function validate() {
		if (!checkedUsername) {
			alert('아이디 중복체크를 해주세요');
		}

		return checkedUsername;
	}

	function usernameCheck() {
		// 성공(ajax)시 true
		var tfUsername = $('#username').val();
		// 		alert(tfUsername);
		console.log(tfUsername);

		$.ajax(
				
			{
				type : 'get',
				url : '/blog/user?cmd=usernameCheck&username='+tfUsername
			
			}
				
		).done(
			function (result) {
				console.log(result);
				if(result == 1){
					alert('아이디가 중복되었습니다.')
				} else if(result == 0){
					alert('사용하실 수 있는 아이디 입니다.')
					checkedUsername = true;
				} else if(result == 2){
					alert('아이디를 입력해주세요.')
				} else {
					console.log('develop : 서버 오류'); // 개발시 테스트로 출력하는  메시지는 프로젝트 종료시 제거한다
				}
			}
		).fail(
			function (error) {
				console.log(error);
			}
		); // 기본적으로 비동기 이기 때문에 코드가 실행되고 콜백이 오기전에 다음줄로 내려간다. 콜백이오면 다시 올라와서 코드를 실행하게 된다.

	}
</script>

<div class="container">
	<form action="/blog/user?cmd=joinProc" method="post" class="was-validated" onsubmit="return validate()">
		<div class="form-group">
			<label for="username">Username:</label> <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
			<button type="button" class="btn btn-info" onclick="usernameCheck()">아이디 중복확인</button>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<div class="form-group">
			<label for="email">Email:</label> <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<div class="form-group">
			<label for="address">Address:</label> <input type="button" onClick="goPopup();" value="주소검색" class="btn btn-primary btn-sm" />
			<!-- 추가 -->

			<input type="text" class="form-control" id="address" placeholder="Enter address" name="address" required readonly>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<button type="submit" class="btn btn-primary">회원가입 완료</button>
	</form>
</div>

<%@include file="../include/footer.jsp"%>