<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<form action="joinProc.jsp" method="post">
	<input type="text" name="username" placeholder="username"/><br/>
	<input type="password" name="password" placeholder="password"/><br/>
	<input type="email" name="email" placeholder="email"/><br/>
	<input type="submit" value="회원가입 완료" />
</form>
</body>
</html>