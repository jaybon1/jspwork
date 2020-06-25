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

<div class="container-fluid" style="font-size: 12px;">
	<h2>훈련진행상황</h2>
	<br />
	<table class="table table-bordered text-center">
		<thead>
			<tr style="font-size: 16px">
				<th> </th>
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
				<td> </td>
				<c:forEach var="pid" items="${pids}">
					<td><b>${pid.className}</b></td>
				</c:forEach>
			</tr>
			<tr>
				<td> </td>
				<c:forEach var="pid" items="${pids}">
					<td><b>${pid.homeroomProf}</b></td>
				</c:forEach>
			</tr>
			
			<c:forEach var="classTime" begin="1" end="8">
				<tr  class="tuple_${classTime }">
					<td rowspan="3" style="height: ${pageScope.tdHeight}px">${classTime }</td>
					<c:forEach var="ppd" items="${ppdsList.get(classTime - 1)}">
						<td>${ppd.subject1}</td>
					</c:forEach>
				</tr>
				<tr class="tuple_${classTime }">
					<c:forEach var="ppd" items="${ppdsList.get(classTime - 1)}">
						<td>${ppd.subject2}</td>
					</c:forEach>
				</tr>
				<tr class="tuple_${classTime }">
					<c:forEach var="ppd" items="${ppdsList.get(classTime - 1)}">
						<td>${ppd.prof}</td>
					</c:forEach>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>

<script type="text/javascript">
	
	function focusTime() {
		let today = new Date();
		
		var classTime = 0;
		
		if(""+today.getHours()+today.getMinutes() >= 900 && ""+today.getHours()+today.getMinutes() < 1000) {
			classTime = 1;
		} else if(""+today.getHours()+today.getMinutes() >= 1000 && ""+today.getHours()+today.getMinutes() < 1100) {
			classTime = 2;
		} else if(""+today.getHours()+today.getMinutes() >= 1100 && ""+today.getHours()+today.getMinutes() < 1200) {
			classTime = 3;
		} else if(""+today.getHours()+today.getMinutes() >= 1200 && ""+today.getHours()+today.getMinutes() < 1340) {
			classTime = 4;
		} else if(""+today.getHours()+today.getMinutes() >= 1340 && ""+today.getHours()+today.getMinutes() < 1440) {
			classTime = 5;
		} else if(""+today.getHours()+today.getMinutes() >= 1440 && ""+today.getHours()+today.getMinutes() < 1540) {
			classTime = 6;
		} else if(""+today.getHours()+today.getMinutes() >= 1540 && ""+today.getHours()+today.getMinutes() < 1640) {
			classTime = 7;
		} else if(""+today.getHours()+today.getMinutes() >= 1640 && ""+today.getHours()+today.getMinutes() < 1740) {
			classTime = 8;
		}

		for (var i = 1; i <= 8; i++) {
			$(".tuple_"+i).css("background", "rgba(255,204,178,.2)");
		}

		$(".tuple_"+classTime).css("background", "rgba(178,204,255,.4)");
		
	}
	
	focusTime();
	setInterval(focusTime, 60000);
	
	
	

</script>

<%@include file="include/footer.jsp"%>