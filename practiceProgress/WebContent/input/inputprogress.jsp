<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../include/nav.jsp"%>

<div class="container">
	<h2>훈련과정등록</h2>
	<p>The .table-striped class adds zebra-stripes to a table:</p>
	<br />
	<form>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">강의실</span>
			</div>
			<input type="text" class="form-control" style="width: 5%" placeholder="숫자로 입력" required="required">
			<div class="input-group-prepend">
				<span class="input-group-text">훈련과정명</span>
			</div>
			<input type="text" class="form-control" style="width: 60%" placeholder="훈련명을 입력해주세요" required="required">

		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">훈련분야</span>
			</div>
			<select name="prog_code" required="required">
				<option value="">선택</option>
				<option value="sw">정보기술개발</option>
				<option value="de">디자인</option>
				<option value="nw">네트웍보안</option>
			</select>
			<div class="input-group-prepend">
				<span class="input-group-text">개강일</span>
			</div>
			<input type="date" required="required">
			<div class="input-group-prepend">
				<span class="input-group-text">종강일</span>
			</div>
			<input type="date" required="required">
			<div class="input-group-prepend">
				<span class="input-group-text">담임교사</span>
			</div>
			<input type="text" class="form-control" placeholder="이름을 입력하세요" required="required">
		</div>
		<div>
			<input class="float-right" type="submit" value="등록하기">
		</div>
	</form>
	<br /> <br /> <br /> <br />
	<h3>현시간 훈련진행상황</h3>
	<table class="table .table-bordered text-center">
		<thead>
			<tr>
				<th>402호</th>
				<th>403호</th>
				<th>404호</th>
				<th>405호</th>
				<th>501호</th>
				<th>502호</th>
				<th>503호</th>
				<th>504호</th>
				<th>505호</th>
				<th>506호</th>
				<th>507호</th>
				<th>508호</th>
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
			<tr style="background-color: rgba(50,50,50,.5);">
				<td>dadf</td>
			</tr>
			
		</tbody>
	</table>
</div>


</body>
</html>