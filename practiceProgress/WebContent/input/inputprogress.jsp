<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../include/nav.jsp"%>

<style>

td {
	max-width: 48px !important;
	overflow: hidden;
}

.nowTimeColor{
	background-color: rgba(200,200,200,.3);
}



</style>

<div class="container-fluid">
	<h2>훈련과정 등록</h2>
	<br />
	<form action="/practiceProgress/classtable?cmd=inputProc" method="post">
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">강의실</span>
			</div>
			<select name="room" required="required">
				<option value="">선택</option>
				<option value="402">402</option>
				<option value="403">403</option>
				<option value="404">404</option>
				<option value="405">405</option>
				<option value="501">501</option>
				<option value="502">502</option>
				<option value="503">503</option>
				<option value="504">504</option>
				<option value="505">505</option>
				<option value="506">506</option>
				<option value="507">507</option>
				<option value="508">508</option>
			</select>
			<div class="input-group-prepend">
				<span class="input-group-text">훈련과정명</span>
			</div>
			<input name="className" type="text" class="form-control" style="width: 60%" placeholder="훈련명을 입력해주세요" required="required">

		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">훈련분야</span>
			</div>
			<select name="classPart" required="required">
				<option value="">선택</option>
				<option value="sw">정보기술개발</option>
				<option value="de">디자인</option>
				<option value="nw">네트웍보안</option>
			</select>
			<div class="input-group-prepend">
				<span class="input-group-text">개강일</span>
			</div>
			<input name="classOpen" type="date" required="required">
			
			<div class="input-group-prepend">
				<span class="input-group-text">종강일</span>
			</div>
			<input name="classClose" type="date" required="required">
			
			<div class="input-group-prepend">
				<span class="input-group-text">담임교사</span>
			</div>
			<input name="homeroomProf" type="text" class="form-control" placeholder="이름을 입력하세요" required="required">
			
		</div>
		<div>
			<input class="float-right" type="submit" value="등록하기">
		</div>
	</form>
	<br /> <br />
	<h3>현시간 훈련진행상황</h3>
	<br />
	<p>호실을 누르면 상세보기 화면을 보실 수 있습니다</p>
	<br />
	<table class="table .table-bordered text-center">
		<thead>
			<tr>
				<th style="cursor: pointer;" onclick="detail(${pids.get(0).room})">402호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(1).room})">403호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(2).room})">404호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(3).room})">405호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(4).room})">501호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(5).room})">502호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(6).room})">503호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(7).room})">504호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(8).room})">505호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(9).room})">506호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(10).room})">507호</th>
				<th style="cursor: pointer;" onclick="detail(${pids.get(11).room})">508호</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach var="pid" items="${pids}">
					<td>${pid.className}</td>
				</c:forEach>

			</tr>
			<tr>
				<c:forEach var="pid" items="${pids}">
					<td>${pid.homeroomProf}</td>
				</c:forEach>
			</tr>
			
			<tr class="nowTimeColor">
				<c:forEach var="ppd" items="${ppds}">
					<td>${ppd.subject1}</td>
				</c:forEach>
			</tr>
			<tr class="nowTimeColor">
				<c:forEach var="ppd" items="${ppds}">
					<td>${ppd.subject2}</td>
				</c:forEach>
			</tr>
			<tr class="nowTimeColor">
				<c:forEach var="ppd" items="${ppds}">
					<td>${ppd.prof}</td>
				</c:forEach>
			</tr>
			
		</tbody>
	</table>
</div>

<script>

	function detail(room) {
		
		if(room == 0) {
			return;
		}
		
		// - (570 / 2) 등은 창 위치 조정을 위함
		
		var popupX = (document.body.offsetWidth / 2) - (570 / 2);

		var popupY = (window.screen.height / 2) - (800 / 2);
		
		var pop = window.open("/practiceProgress/classtable?cmd=detail&room="+room, "pop",
		"width=570, height=700, left="+ popupX + ", top="+ popupY+", scrollbars=yes, resizable=yes");
		
	}

</script>

<%@include file="../include/footer.jsp"%>