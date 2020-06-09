function deleteById(boardId) { // 주소에 값이 노출되지만 delete이기 때문에 post로 전송
	$.ajax({

		type : "POST",
		url : `/blog/board?cmd=delete&id=${boardId}`,
		dataType : "text"

	}).done(function(result) {

		if (result == 1) {
			alert("삭제 성공");
			location.href = "/blog/board?cmd=home&page=0";
		} else {
			alert("삭제 실패");
		}

	}).fail(function(result) {

		alert("서버 오류");

	}).always(function(result) {

	});
}

function back(page) {
	
	location.href = "/blog/board?cmd=home&page=" + page;
	
}
