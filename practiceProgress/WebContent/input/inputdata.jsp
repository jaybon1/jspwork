<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/nav.jsp"%>

<div class="container">
  <h2>데이터등록</h2>
  <p>아래 사항들을 확인하고 세팅해주세요</p>            
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
      <tr>
        <td>402호</td>
        <td>john@example.com</td>
        <td>2020-06-08~12-30</td>
        <td>홍길동</td>
        <td>
        	<button type="button" class="btn btn-warning btn-sm">변경</button>
			<button type="button" class="btn btn-danger btn-sm">비활성화</button>
		</td>
      </tr>
    </tbody>
  </table>
  <br/>
  <br/>
  <div class="col text-center">  
		<button type="button" class="btn btn-outline-primary" onclick="">추가하기</button>
  </div>
</div>