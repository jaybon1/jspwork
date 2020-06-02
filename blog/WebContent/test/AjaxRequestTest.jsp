<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
div {
	border: 1px solid black;
	margin: 5px;
	padding: 5px;
}
</style>
</head>
<body>

	<div id="reply-box">
		<div id="reply-1" class="re">첫번째 댓글 입니다.</div>
	</div>

	<input type="text" id="tf-reply" />
	<button onclick="start()">댓글쓰기</button>

	<script>
		var num = 1; // 이 값은 원래 db에서 가져와야됨
		function start() {
			num++;
			var a = $('#tf-reply').val();

			// 통신이 성공하면 아래 로직 실행

			$.ajax(
			// ajax 비동기 백그라운드로 다른페이지에 request를 하고 response를 받으면 result에 데이터를 저장한다. 
			// 보낼타입은 json 상대방에게 받을 타입도 json이다. 아래에서 확인

			{ // 오브젝트
				type : 'post',
				url : 'AjaxResponseTest.jsp',
				data : '{"username":"ssar", "password":"1234"}',
				contentType : 'application/json; charset=utf-8',
				dataType : 'json' // 받을데이터를 어떻게 파싱할까 text / json
			}

			).done(
					function(result) { // 통신이 성공
						console.log(result);
						$('#reply-box').prepend(
								"<div id='reply-" + num + "'>" + a + "</div>");
					}).fail(function(error) { // 통신이 실패
				console.log(error);
			});

		}
	</script>

</body>
</html>