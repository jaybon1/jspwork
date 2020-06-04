/**
 * js파일 안에는 <script>를 쓰지 않아도 된다
 */

var checkedUsername = false;

//const goPopup = () => {
//	
//	// 최근에는 이러한 방식으로 함수를 선언한다.
//	
//}


// juso.go.kr 라이브러리 함수 (시작) ----------------------------------------
function goPopup() {
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제
	// 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/blog/juso/jusoPopup.jsp", "pop",
			"width=570,height=420, scrollbars=yes, resizable=yes");

	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제
	// 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게
	// 됩니다.
	// var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes,
	// resizable=yes");
}

function jusoCallBack(roadFullAddr) {
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.

	// document.getElementById("address").value = roadFullAddr; -> 잘됨

	// document.myForm.address.value = roadFullAddr; -> 잘됨

	var temp = document.querySelector("#address");
	temp.value = roadFullAddr;

}


// 중복체크 함수
function validate() {
	if (!checkedUsername) {
		alert('아이디 중복체크를 해주세요');
	}

	return checkedUsername;
}


// 데이터베이스에 ajax 요청을 해서 중복 유저네임이 있는지 확인
// 있으면 1을 리턴, 없으면 0을 리턴, 오류가 나면 -1을 리턴
function usernameCheck() {
	
	// 성공(ajax)시 true
	var tfUsername = $('#username').val();
	// alert(tfUsername);
	console.log(tfUsername);

	$.ajax(

	{
		type : 'get',
		url : `/blog/user?cmd=usernameCheck&username=${tfUsername}`

	}

	).done(function(result) {
		console.log(result);
		if (result == 1) {
			alert('아이디가 중복되었습니다.')
		} else if (result == 0) {
			alert('사용하실 수 있는 아이디 입니다.')
			checkedUsername = true;
		} else if (result == 2) {
			alert('아이디를 입력해주세요.')
		} else {
			console.log('develop : 서버 오류'); // 개발시 테스트로 출력하는 메시지는 프로젝트
			// 종료시 제거한다
		}
	}).fail(function(error) {
		console.log(error);
	}); // 기본적으로 비동기 이기 때문에 코드가 실행되고 콜백이 오기전에 다음줄로 내려간다. 콜백이오면 다시 올라와서 코드를
	// 실행하게 된다.

}