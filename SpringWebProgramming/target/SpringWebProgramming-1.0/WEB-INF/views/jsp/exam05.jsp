<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html> 
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>JSP Page</title>
		<link href="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%= application.getContextPath() %>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	</head>
	<body>
		<h3></h3>
		이름: ${name3} <br/>
		멤버 이름: ${member3.name}<br/>
		멤버 나이: ${member3.age}<br/>
		<hr/>
		<h3>게시물 정보</h3>
		번호: ${board.bno} <br/>
		제목 ${board.btitle} <br/>
		내용: ${board.bcontent} <br/>
		글쓴이: ${board.bwriter} <br/>
		날짜: ${board.bdate} <br/>
		
		<h3>게시물 정보</h3>
		<table class="table table-bordered" style="width: 80%">
			<tr class="success">
				<td>번호</td>
				<td>제목</td>
				<td>내용</td>
				<td>글쓴이</td>
				<td>날짜</td>
			</tr>
			<c:forEach var="b" items="${list}">
				<tr>
					<td>${b.bno}</td>
					<td>${b.btitle}</td>
					<td>${b.bcontent}</td>
					<td>${b.bwriter}</td>
					<td>${b.bdate}</td>			
				</tr>
			</c:forEach>
		</table>
	
	</body>
</html>

