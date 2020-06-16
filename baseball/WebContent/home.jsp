<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>야구선수 정보</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-3">
       	  <h2>팀 목록</h2>           
		  <table class="table table-hover">
		    <thead>
		      <tr>
		        <th>번호</th>
		        <th>팀 이름</th>
		      </tr>
		    </thead>
		    <tbody>
		    	<c:forEach var="team" items="${teamList}">
			      <tr>
			        <td>${team.id}</td>
			        <td onclick="getPlayer('${team.teamName}')" style="cursor: pointer;">${team.teamName}</td>
			      </tr>
		    	</c:forEach>
		    </tbody>
		  </table>
        </div>
        <div class="col-sm-3">
        	<h2>선수 목록</h2>           
		  <table class="table table-success">
		    <thead>
		      <tr>
		        <th>선수 번호</th>
		        <th>선수 이름</th>
		      </tr>
		    </thead>
		    <tbody id="player__list">
		    </tbody>
		  </table>
        </div>
        <div class="col-sm-6">
        	<h2>선수 상세</h2>           
		  <table class="table table-info">
		    <thead>
		      <tr>
		        <th>선수 번호</th>
		        <th>선수 이름</th>
		        <th>포지션</th>
		      </tr>
		    </thead>
		    <tbody id="player__desc">
		    </tbody>
		  </table>
        </div>
    </div>
</div>

<script type="text/javascript">

	function getPlayer(teamName) {
		$.ajax({
			type: "get",
			url: "/baseball/player?cmd=getPlayer&teamName="+teamName,
			dataType: "json"
		}).done(function(result) {
			
			$("#player__list").empty();
			for (let player of result) {
				var string =
					"			      <tr>\r\n" + 
					"			        <td>"+player.playerNum+"</td>\r\n" + 
					"			        <td onclick=\"getDesc('"+player.playerNum+"','"+teamName+"')\" style=\"cursor: pointer;\">"+player.playerName+"</td>\r\n" + 
					"			      </tr>";
					
				$("#player__list").append(string);
					
			}
			
			$("#player__desc").empty();
			
		}).fail(function(result) {
			alert("검색에 실패하였습니다.");
		});
	}
	
	function getDesc(playerNum, teamName) {
		
		$.ajax({
			type: "get",
			url: "/baseball/player?cmd=getDesc&teamName="+teamName+"&playerNum="+playerNum,
			dataType: "json"
		}).done(function(result) {
			
			$("#player__desc").empty();

			var string =
				"			      <tr>\r\n" + 
				"			        <td>"+result.playerNum+"</td>\r\n" + 
				"			        <td>"+result.playerName+"</td>\r\n" + 
				"			        <td>"+result.position+"</td>\r\n" +
				"			      </tr>";
				
			$("#player__desc").append(string);

		}).fail(function(result) {
			alert("검색에 실패하였습니다.");
		});
		
	}

</script>

</body>
</html>