<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="include/nav.jsp"%>

<%
	pageContext.setAttribute("tdHeight", 60); //높이 조절
%>

<style>

.table td {
	padding: 2px;
}

.table thead th {
	padding: 2px;
}

td {
	max-width: 48px !important;
	overflow: hidden;
}

</style>

<div class="container-fluid" style="font-size: 10px;">
	<h2>훈련진행상황</h2>
	<br />
	<table class="table table-bordered text-center">
		<thead>
			<tr style="font-size: 16px">
				<th> </th>
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
				<td> </td>
				<c:forEach var="pid" items="${pids}">
					<td>${pid.className}</td>
				</c:forEach>

			</tr>
			<tr>
				<td> </td>
				<c:forEach var="pid" items="${pids}">
					<td>${pid.homeroomProf}</td>
				</c:forEach>
			</tr>
			<tr>
				<td rowspan="3" style="height: ${pageScope.tdHeight}px">1</td>
				<c:forEach var="ppd" items="${ppdsList.get(0)}">
					<td>${ppd.subject1}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(0)}">
					<td>${ppd.subject2}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(0)}">
					<td>${ppd.prof}</td>
				</c:forEach>
			</tr>
			
			<tr>
				<td rowspan="3" style="height: ${pageScope.tdHeight}px">2</td>
				<c:forEach var="ppd" items="${ppdsList.get(1)}">
					<td>${ppd.subject1}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(1)}">
					<td>${ppd.subject2}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(1)}">
					<td>${ppd.prof}</td>
				</c:forEach>
			</tr>
			
			<tr>
				<td rowspan="3" style="height: ${pageScope.tdHeight}px">3</td>
				<c:forEach var="ppd" items="${ppdsList.get(2)}">
					<td>${ppd.subject1}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(2)}">
					<td>${ppd.subject2}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(2)}">
					<td>${ppd.prof}</td>
				</c:forEach>
			</tr>
			
			<tr>
				<td rowspan="3" style="height: ${pageScope.tdHeight}px">4</td>
				<c:forEach var="ppd" items="${ppdsList.get(3)}">
					<td>${ppd.subject1}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(3)}">
					<td>${ppd.subject2}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(3)}">
					<td>${ppd.prof}</td>
				</c:forEach>
			</tr>
			
			<tr>
				<td rowspan="3" style="height: ${pageScope.tdHeight}px">5</td>
				<c:forEach var="ppd" items="${ppdsList.get(4)}">
					<td>${ppd.subject1}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(4)}">
					<td>${ppd.subject2}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(4)}">
					<td>${ppd.prof}</td>
				</c:forEach>
			</tr>
			
			<tr>
				<td rowspan="3" style="height: ${pageScope.tdHeight}px">6</td>
				<c:forEach var="ppd" items="${ppdsList.get(5)}">
					<td>${ppd.subject1}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(5)}">
					<td>${ppd.subject2}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(5)}">
					<td>${ppd.prof}</td>
				</c:forEach>
			</tr>
			
			<tr>
				<td rowspan="3" style="height: ${pageScope.tdHeight}px">7</td>
				<c:forEach var="ppd" items="${ppdsList.get(6)}">
					<td>${ppd.subject1}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(6)}">
					<td>${ppd.subject2}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(6)}">
					<td>${ppd.prof}</td>
				</c:forEach>
			</tr>
			
			<tr>
				<td rowspan="3" style="height: ${pageScope.tdHeight}px">8</td>
				<c:forEach var="ppd" items="${ppdsList.get(7)}">
					<td>${ppd.subject1}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(7)}">
					<td>${ppd.subject2}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach var="ppd" items="${ppdsList.get(7)}">
					<td>${ppd.prof}</td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
</div>
<%@include file="include/footer.jsp"%>