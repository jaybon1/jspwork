$("#img__preview").on("change", (e) => {
	var f = e.target.files[0];
	
	console.log(f);

	if(!f.type.match("image*")){
		alert("이미지만 첨부 가능합니다.");
		$("#img__preview").val(""); // 이미지 파일이 아닐 경우 선택된 파일 없음 유지
		return;
	}

	if(f.size > 1024*1024*2){
		alert("용량을 초과 하였습니다.");
		$("#img__preview").val("");
		return;
	}
	
	var reader = new FileReader();
	
	// readAsDataURL(f)보다 위에 설정

	reader.onload = function (e) { // readAsDataURL(f)보다 아래에 있으면 타이밍이 안맞을 수 있음.
		$("#img__wrap").attr("src", e.target.result);
	}

	reader.readAsDataURL(f); // 비동기 실행
	

	// reader.readAsDataURL(f); 가 실행이 끝나면 e.target(이벤트객체)에 result라는 객체를 하나
	// 생성하고 안에 이미지를 넣어준다.
	// $("#img__wrap").attr("src", e.target.result); // reader가 다읽으면 result가
	// 생성된다.(비동기이기때문에 작동안될 것)

});