<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>JSP Page</title>
<link
	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
</head>
<body>
	<h4>게시물 목록</h4>
	<div style="text-align: center">
		<table class="table table-bordered" style="width: 700px;">
			<tr class="success">
				<td style="width: 25%">아이디</td>
				<td style="width: 25%">이름</td>
				<td style="width: 25%">나이</td>
				<td style="width: 25%">주소</td>				
			</tr>
			<c:forEach var="m" items="${list}">
				<tr>
					<td><a href="exam06Detail?mid=${m.mid}">${m.mid}</a></td>
					<td>${m.mname}</td>
					<td>${m.mage}</td>
					<td>${m.maddress}</td>
				</tr>
			</c:forEach>
		</table>
<%-- 		<a href="exam06?pageNo=1">◀</a>
		<c:if test="${groupNo > 1}" >
			<a href="exam06?pageNo=${startPageNo-1}">◁</a>	
		</c:if>
		<c:if test="${groupNo == 1}">
			<a href="exam06?pageNo=1">◁</a>
		</c:if>
		<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">
			<a href="exam06?pageNo=${i}">${i}</a>
		</c:forEach>
		<c:if test="${groupNo >= totalPageNo}" >
			<a href="exam06?pageNo=${endPageNo}">▷</a>	
		</c:if>
		<c:if test="${groupNo < totalPageNo }">
			<a href="exam06?pageNo=${endPageNo+1}">▷</a>
		</c:if>
		<a href="exam06?pageNo=${totalPageNo}">▶</a> --%>
		
		<div style="margin-top: 20px; width: 700px">
			<a href="exam06?pageNo=1">[처음]</a>
			<c:if test="${groupNo>1}">
				<a href="exam06?pageNo=${startPageNo-1}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">			
				&nbsp;
				<a href="exam06?pageNo=${i}" <c:if test="${pageNo==i}">style="font-weight: bold; color: red"</c:if>>${i}</a>
				&nbsp;
			</c:forEach>

			<c:if test="${groupNo<totalGroupNo}">
				<a href="exam06?pageNo=${endPageNo+1}">[다음]</a>
			</c:if>
			<a href="exam06?pageNo=${totalPageNo}">[맨끝]</a>
		</div>
	</div>
</body>
</html>