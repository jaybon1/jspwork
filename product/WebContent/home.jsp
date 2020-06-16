<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="container">
		<h2>Product</h2>
		<p>The .table-striped class adds zebra-stripes to a table:</p>

		<div class="btn-group">
			<button onclick="goFirst()" type="button" class="btn btn-primary">처음으로</button>
			<button onclick="priceAsc()" type="button" class="btn btn-success">가격 오름차순</button>
			<button onclick="priceDesc()" type="button" class="btn btn-warning">가격 내림차순</button>
			<button onclick="countDesc()" type="button" class="btn btn-danger">판매순</button>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>종류</th>
					<th>가격</th>
					<th>판매수</th>
					<th>	</th>
				</tr>
			</thead>
			<tbody id="my__tbody">
				<c:forEach var="product" items="${products}">
					<tr id="product-${product.id}">
						<td>${product.id}</td>
						<td>${product.name}</td>
						<td>${product.type}</td>
						<td>${product.price}</td>
						<td>${product.count}</td>
						<td><i class="large material-icons" onclick="deleteItem(${product.id}, 'goFirst')" style="cursor:pointer;">delete_forever</i></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
<script>

	function append(result, listType) {
		for (var product of result){
			
			var string =
				"					<tr id=\"product-"+product.id+"\">\r\n" + 
				"						<td>"+product.id+"</td>\r\n" + 
				"						<td>"+product.name+"</td>\r\n" + 
				"						<td>"+product.type+"</td>\r\n" + 
				"						<td>"+product.price+"</td>\r\n" + 
				"						<td>"+product.count+"</td>\r\n" + 
				"						<td><i class=\"large material-icons\" onclick=\"deleteItem("+product.id+")\" style=\"cursor:pointer;\">delete_forever</i></td>" +
				"					</tr>";
			
			$('#my__tbody').append(string);
		}
	}

	function deleteItem(productId) {
		
		console.log(productId);
		
		$.ajax({
			
			type : "get",
			url : "/product/test?cmd=deleteProc&productId="+productId,
			dataType : "json"
			
		}).done(function (result) {
			if(result == "1"){
				$("#product-"+productId).remove();
				alert("삭제에 성공하였습니다.");				
			} else{
				alert("삭제에 실패하였습니다.");
			}
			
// 			$("#my__tbody").empty();
// 			append(result);

		}).fail(function (result) {
			alert("삭제에 실패하였습니다.");
		});
		
	}

	function goFirst() {
		
		$.ajax({
			
			type : "get",
			url : "/product/test?cmd=goFirstProc",
			dataType : "json"
			
		}).done(function (result) {
			
			$("#my__tbody").empty();
			append(result);
			
		}).fail(function (result) {
			
		});
		
	}
	
	function priceAsc() {
		
		$.ajax({
			
			type : "get",
			url : "/product/test?cmd=priceAscProc",
			dataType : "json"
			
		}).done(function (result) {
			
			$("#my__tbody").empty();
			append(result);
			
		}).fail(function (result) {
			
		});
		
	}
	
	
	function priceDesc() {
		
		$.ajax({
			
			type : "get",
			url : "/product/test?cmd=priceDescProc",
			dataType : "json"
			
		}).done(function (result) {
			
			$("#my__tbody").empty();
			append(result, "priceDesc");
			
		}).fail(function (result) {
			
		});
	}
	
	
	function countDesc() {
		
		$.ajax({
			
			type : "get",
			url : "/product/test?cmd=countDescProc",
			dataType : "json"
			
		}).done(function (result) {
			
			$("#my__tbody").empty();
			append(result);
			
		}).fail(function (result) {
			
		});
	}

</script>	
	
</body>
</html>
