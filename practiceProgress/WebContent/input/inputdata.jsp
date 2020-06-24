<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/nav.jsp"%>

	<div class="container">
		<h2>엑셀 데이터 등록</h2>
		<p>아래 사항들을 확인하고 엑셀 데이터를 세팅해주세요</p>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>호실</th>
					<th>수업명</th>
					<th>기간</th>
					<th>담임</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="trueClassTable" items="${trueClassTables}">
					<tr>
						<td>${trueClassTable.room}호</td>
						<td>${trueClassTable.className }</td>
						<td>${trueClassTable.classOpen} ~ ${trueClassTable.classClose}</td>
						<td>${trueClassTable.homeroomProf }</td>
						<td>
							<button type="button" class="btn btn-outline-danger btn-sm" onclick="deActivateProc(${trueClassTable.id})">비활성화</button> <c:choose>
								<c:when test="${empty trueClassTable.excelName }">
									<button type="button" class="btn btn-outline-primary btn-sm" onclick="addExcel(${trueClassTable.id})">등록하기</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-outline-warning btn-sm">변경하기</button>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <br />
		<div class="col text-center"></div>
	</div>
	<br/>
	<br/>
	<div class="container">
		<h2>비활성화 과정</h2>
		<br />
		<table class="table table-striped">
			<thead>
				<tr>
					<th>호실</th>
					<th>수업명</th>
					<th>기간</th>
					<th>담임</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="falseClassTable" items="${falseClassTables}">
					<tr>
						<td>${falseClassTable.room}호</td>
						<td>${falseClassTable.className }</td>
						<td>${falseClassTable.classOpen}~ ${falseClassTable.classClose}</td>
						<td>${falseClassTable.homeroomProf }</td>
						<td>
							<button type="button" class="btn btn-outline-danger btn-sm" onclick="activateProc(${falseClassTable.id})">활성화</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <br />
		<div class="col text-center"></div>
	</div>
	
	<script type="text/javascript">
	
		function addExcel(id) {
			
			// - (570 / 2) 등은 창 위치 조정을 위함
			
			var popupX = (document.body.offsetWidth / 2) - (570 / 2);

			var popupY = (window.screen.height / 2) - (800 / 2);
			
			var pop = window.open("/practiceProgress/practicetable?cmd=addExcel&id="+id, "pop",
			"width=570, height=630, left="+ popupX + ", top="+ popupY+", scrollbars=yes, resizable=yes");
			
		}
	
		function deActivateProc(id) {
			
			$.ajax({
				
				type: "get",
				url: "/practiceProgress/practicetable?cmd=deActivateProc&id="+id,
				dataType: "text"
				
			}).done(function(result) {
				
				alert("비활성화 되었습니다.");
				location.reload();
				
			}).fail(function(error) {
				
				alert("비활성화에 실패하였습니다.");
				
			});
			
		}
	
		function activateProc(id) {
			
			$.ajax({
				
				type: "get",
				url: "/practiceProgress/practicetable?cmd=activateProc&id="+id,
				dataType: "text"
				
			}).done(function(result) {
				
				alert("활성화 되었습니다.");
				location.reload();
				
			}).fail(function(error) {
				
				alert("활성화에 실패하였습니다.");
				
			});
			
		}
		
	</script>
	
<%@include file="../include/footer.jsp"%>