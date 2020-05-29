<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script>
		function move() {
			location.href="first.jsp";			
		}
		
		function move1() {
			location.href="/satBlog/test?cmd=di";			
		}
	</script>
	
</head>
<body>

	<a href="first.jsp">html a href 에 주소를 넣는 방식</a>
	
	<br/>
	<br/>
	
	<input type="button" onclick="move()" value="자바스크립트 방식">
	
	<br/>
	<br/>
	
	<a href="/satBlog/test?cmd=re">리다이렉트 방법</a>
	
	<br/>
	<br/>
	
	<a href="/satBlog/test?cmd=di">디스패쳐 방법</a>
	
	<br/>
	<br/>
	
	<input type="button" onclick="move1()" value="자바스크립트 + 디스패처 방식">
	
</body>
</html>

