<%@page import="com.cos.blog.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	// 	Users principal = (Users) session.getAttribute("principal");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>blog</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="/blog/css/styles.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/blog/index.jsp">Blog</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="justify-content-between collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">

				<c:choose>
					<c:when test="${empty sessionScope.principal}">

						<li class="nav-item"><a class="nav-link" href="/blog/user?cmd=login">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user?cmd=join">회원가입</a></li>

					</c:when>

					<c:otherwise>

						<li class="nav-item"><a class="nav-link" href="/blog/board?cmd=write">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user?cmd=update">회원정보</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user?cmd=logout">로그아웃</a></li>

					</c:otherwise>
					
				</c:choose>

			</ul>

			<ul class="navbar-nav">

				<c:if test="${not empty sessionScope.principal}">
					<li class="nav-item">
					
						<a href="/blog/user?cmd=profileUpload"> 
						
							<img style="border-radius: 20px" onerror="this.src='/blog/image/userProfile.png'"
								src="${sessionScope.principal.userProfile}" width="40px" height="40px" />
						</a>
					</li>
				</c:if>

			</ul>

		</div>
	</nav>
	<br>