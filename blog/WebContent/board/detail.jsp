<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>

<div class="container">

	<button type="button" class="btn btn-light" onclick="back(${param.page})">뒤로가기</button>

	<c:if test="${sessionScope.principal.id == detailDto.boardDto.board.userId}">

		<a href="/blog/board?cmd=update&id=${detailDto.boardDto.board.id}" class="btn btn-warning">수정</a>
		<!-- 하이퍼링크는 get방식만 사용 -->
		<button type="button" class="btn btn-danger" onclick="deleteById(${detailDto.boardDto.board.id})">삭제</button>

	</c:if>


	<br /> <br />

	<h6>
		작성자 : <i>${detailDto.boardDto.username}</i> 조회수 : <i>${detailDto.boardDto.board.readCount}</i>
	</h6>

	<br />

	<h3>${detailDto.boardDto.board.title}</h3>

	<div class="container p-3 my-3 border">${detailDto.boardDto.board.content}</div>

	<hr />
	<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2">
						<b>Comment</b>
					</div>
					<div class="panel-body">
						<textarea id="reply__write__form" class="form-control" placeholder="write a comment..." rows="3"></textarea>
						<br>
						<button onclick="replyWrite(${detailDto.boardDto.board.id}, ${sessionScope.principal.id})" type="button" class="btn btn-primary pull-right">댓글쓰기</button>
						<div class="clearfix"></div>
						<hr />
						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">
							<c:forEach var="replyDto" items="${detailDto.replyDtos}">
								<!-- 댓글 아이템 -->
								<li id="reply-${replyDto.reply.id}" class="media"><img onerror="this.src='/blog/image/userProfile.png'" src="${replyDto.userProfile}" alt="" class="img-circle">
									<div class="media-body">
										<strong class="text-primary">${replyDto.username}</strong>
										<p>
											${replyDto.reply.content} <br /> <br />
										</p>
									</div>
									<div class="m-3">
										<c:if test="${replyDto.reply.userId eq sessionScope.principal.id}">
											<i onclick="replyDelete(${replyDto.reply.id})" class="fa fa-remove" style="cursor: pointer; font-size:30px;color:red;"></i>
										</c:if>
									</div>
								</li>
							</c:forEach>
						</ul>
						<!-- 댓글 리스트 끝-->
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 댓글 박스 끝 -->

</div>

<script>

	function replyDelete(replyId) {
		
		$.ajax({
			
			type: "post",
			url: "/blog/reply?cmd=deleteProc",
			data : "replyId=" + replyId,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "text"
			
		}).done(function (result) {
			if(result == "1") {
				var replyItem = $("#reply-"+replyId);
				replyItem.remove();
				alert("댓글 삭제 성공");
			} else {
				alert("댓글 삭제 실패");
			}

			
		}).fail(function (result) {
			alert("댓글 삭제 실패");
		});
		
		
	}

	function replyWrite(boardId, userId) {
		
		if(userId == undefined) {
			alert("로그인이 필요합니다.");
			return;
		}
		
		
		var data = {
			boardId : boardId,  // 키값은 변수가 안 들어가서 문제 없다
			userId : userId,
			content : $("#reply__write__form").val()
		}
		
		$.ajax({
			
			type: "post",
			url: "/blog/reply?cmd=writeProc",
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			dataType: "json"
			
		}).done(function (result) {
			
			$("#reply__list").empty();
			
			// 정상 응답
			// 1. reply__list를 찾아서 내부를 비우기
			// 2. ajax 재호출 findAll()
			// 3. reply__list를 찾아서 내부에 채워주기

			for (var replyDto of result) {
				
				if(userId == replyDto.reply.userId){
					
					var string = 
						"<li id=\"reply-"+replyDto.reply.id+"\" class=\"media\">"+
						"	<img onerror=\"this.src='/blog/image/userProfile.png'\" src=\""+replyDto.userProfile+"\" alt=\"\" class=\"img-circle\">\r\n" + 
						"	<div class=\"media-body\">\r\n" + 
						"		<strong class=\"text-primary\">"+replyDto.username+"</strong>\r\n" + 
						"		<p>\r\n" + 
						"			"+replyDto.reply.content+" <br /> <br />\r\n" + 
						"		</p>\r\n" + 
						"	</div>"+
						"	<div class=\"m-3\">\r\n" +
						"		<i onclick=\"replyDelete("+replyDto.reply.id+")\" class=\"fa fa-remove\" style=\"cursor:pointer;font-size:30px;color:red;\"></i>\r\n" + 
						"	</div>"+
						"</li>";
						  
					$('#reply__list').append(string);
					
				} else{
					
					var string =
						"<li id=\"reply-"+replyDto.reply.id+"\" class=\"media\">"+
						"	<img onerror=\"this.src='/blog/image/userProfile.png'\" src=\""+replyDto.userProfile+"\" alt=\"\" class=\"img-circle\">\r\n" + 
						"	<div class=\"media-body\">\r\n" + 
						"		<strong class=\"text-primary\">"+replyDto.username+"</strong>\r\n" + 
						"		<p>\r\n" + 
						"			"+replyDto.reply.content+" <br /> <br />\r\n" + 
						"		</p>\r\n" + 
						"	</div>"+
						"	<div class=\"m-3\">\r\n" +
						"	</div>"+
						"</li>";
					  
					$('#reply__list').append(string);
				}
				
				

			
			}
			
// 			다른방법
// 		    result.forEach(replyDto => {
	    	
//				var string = "<li class=\"media\"><img onerror=\"this.src='/blog/image/userProfile.png'\" src=\""+replyDto.userProfile+"\" alt=\"\" class=\"img-circle\">\r\n" + 
//				"					<div class=\"media-body\">\r\n" + 
//				"					<strong class=\"text-primary\">"+replyDto.username+"</strong>\r\n" + 
//				"					<p>\r\n" + 
//				"						"+replyDto.reply.content+" <br /> <br />\r\n" + 
//				"					</p>\r\n" + 
//				"				</div></li>";
			  
//			$('#reply__list').append(string);
	        
//		    });
			
		}).fail(function (result) {
			
		});
		
		
// 		$.ajax({
// 			type: "post",
// 			url: "/~~/~",
// 			success: function name() {}, 
// 			error : function name() {}
// 		});
		
	}
</script>

<script src="/blog/js/detail.js"></script>

<%@include file="../include/footer.jsp"%>


