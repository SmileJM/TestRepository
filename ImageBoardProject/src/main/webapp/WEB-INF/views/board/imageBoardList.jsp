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
	<style>
	a {
		color: gray;
	}
	a:HOVER {
		color: red;
		text-decoration: none;
	}
	.glyphicon-th-list, .glyphicon-th-large {
	    font-size: 30px;
	}
	</style>
<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
	<script>
	function handleBtnSearch() {
		location.href="imageBoardSearch?btitle=${bsearch}";
	}
	</script>
</head>
<body>
	<div style="width: 1000px; margin: auto; text-align: center">
		<h4>게시물 목록</h4>
		<div style="width: 1000px; margin: auto; text-align: right">

		<a class="glyphicon glyphicon-th-list"  href="imageBoardList?type=1"></a>
		<a class="glyphicon glyphicon-th-large" href="imageBoardList?type=2" ></a>

		</div>
		<c:if test="${param.type==1 }">
		<input type="hidden"  name="type" value="1"/>
		<table class="table table-bordered table-hover" 
			style="width: 1000px; text-align: center; border: 0px;">
			<tr class="info">
				<td style="width: 6%;  border-left: 0px; border-right: 0px">번호</td>
				<td style="width: 60%;  border-left: 0px; border-right: 0px">제목</td>
				<td style="width: 13%;  border-left: 0px; border-right: 0px">글쓴이</td>
				<td style="width: 9%;  border-left: 0px; border-right: 0px">날짜</td>
				<td style="width: 6%;  border-left: 0px; border-right: 0px">조회수</td>
				<td style="width: 6%;  border-left: 0px; border-right: 0px">추천수</td>
			</tr>
			<c:forEach var="b" items="${list}">
				<tr>
					<td style="line-height: 70px;  border-left: 0px; border-right: 0px">${b.bno}</td>
					<td style="text-align: left;  border-left: 0px; border-right: 0px">
						<a href="imageBoardDetail?type=${param.type }&bno=${b.bno}" >
							<img src="imageBoardImage?bno=${b.bno }" width="70px" height="70px"/> &nbsp;
							${b.btitle}				
							<c:set var="now" value="<%= new java.util.Date() %>"/> 
							<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd"/>
							<c:if test="${b.bdate == today}"><img src="../resources/image/new03.gif" style="width: 30px"/></c:if>
							<c:if test="${b.blikecount >= 10}"><img src="../resources/image/hot01.gif" style="width: 30px"/></c:if>
						</a>
					</td>					
					<td style="line-height: 70px;  border-left: 0px; border-right: 0px">${b.bwriter}</td>
					<td style="line-height: 70px;  border-left: 0px; border-right: 0px">${b.bdate}</td>
					<td style="line-height: 70px;  border-left: 0px; border-right: 0px">${b.bhitcount}</td>
					<td style="line-height: 70px;  border-left: 0px; border-right: 0px">${b.blikecount}</td>
				</tr>
			</c:forEach>
		</table>
<!-- 		<div  class="input-group"  style="margin-top: 20px; width: 1000px">
			<input type="text" name="bsearch" id="bsearch" placeholder="검색"	style="background-color: white; width:200px; height: 33px">			
			<a type="button"  class="btn btn-info"  href="imageBoardSearch?bsearch=김">검색</a>
			<input type="button"  class="btn btn-info" value="검색 " href="imageBoardList"  onclick="handleBtnSearch()" />
		</div> -->
		<div  class="input-group"  style="margin-top: 20px; width: 1000px">
			<a href="imageBoardList?type=1&pageNo=1">[처음]</a>
			<c:if test="${groupNo>1}">
				<a href="imageBoardList?type=1&pageNo=${startPageNo-1}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">			
				&nbsp;
				<a href="imageBoardList?type=1&pageNo=${i}"
					<c:if test="${pageNo==i}">style="font-weight: bold; color: red"</c:if>>${i}</a>
				&nbsp;
			</c:forEach>

			<c:if test="${groupNo<totalGroupNo}">
				<a href="imageBoardList?type=1&pageNo=${endPageNo+1}">[다음]</a>
			</c:if>
			<a href="imageBoardList?type=1&pageNo=${totalPageNo}">[맨끝]</a>
		</div>
		<div style="margin-top: 10px; width: 700; text-align: right">
			<a href="imageBoardWrite?type=${param.type }" class="btn btn-success">글쓰기</a>
		</div>
		</c:if>
		
		<c:if test="${param.type==2 }">
		<input type="hidden"  name="type" value="2"/>
		<div style="width: 1000px; text-align: left; border: 0px;">
		 	<c:forEach var="b" items="${list}" >
				<div style="width: 200px;  height: 330px; float: left;">
					<a href="imageBoardDetail?type=${param.type }&bno=${b.bno}">
						<img src="imageBoardImage?bno=${b.bno }" width="180px" height="200px" style="margin-bottom: 10px;"/></a>	
					<span class="glyphicon glyphicon glyphicon-user input-group"  style="margin-bottom: 10px; margin-left: 10px; color: skyblue;"> ${b.bwriter}</span> 
					<span class="glyphicon glyphicon glyphicon-eye-open"  style="margin-bottom: 10px; margin-left: 10px;color: skyblue"> ${b.bhitcount}</span> 
					<span class="glyphicon glyphicon glyphicon-heart" style="margin-bottom: 10px; margin-left: 10px; color: skyblue"> ${b.blikecount}</span>
					<a href="imageBoardDetail?type=${param.type }&bno=${b.bno}">
						<span class="glyphicon glyphicon glyphicon-tags input-group" style="margin-bottom: 30px; margin-left: 10px; height: 50px" > ${b.btitle}
							<c:set var="now" value="<%= new java.util.Date() %>"/> 
							<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd"/>
							<c:if test="${b.bdate == today}"><img src="../resources/image/new03.gif" style="width: 30px"/></c:if>
							<c:if test="${b.blikecount >= 10}"><img src="../resources/image/hot01.gif" style="width: 30px"/></c:if>
						</span>
					</a>				

				</div>
			</c:forEach> 
		</div>
		<div  class="input-group"  style="margin-top: 20px; width: 1000px">
			<a href="imageBoardList?type=2&pageNo=1">[처음]</a>
			<c:if test="${groupNo>1}">
				<a href="imageBoardList?type=2&pageNo=${startPageNo-1}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPageNo}" end="${endPageNo}">			
				&nbsp;
				<a href="imageBoardList?type=2&pageNo=${i}"
					<c:if test="${pageNo==i}">style="font-weight: bold; color: red"</c:if>>${i}</a>
				&nbsp;
			</c:forEach>

			<c:if test="${groupNo<totalGroupNo}">
				<a href="imageBoardList?type=2&pageNo=${endPageNo+1}">[다음]</a>
			</c:if>
			<a href="imageBoardList?type=2&pageNo=${totalPageNo}">[맨끝]</a>
		</div>
		<div style="margin-top: 10px; width: 700; text-align: right">
			<a href="imageBoardWrite?type=${param.type }" class="btn btn-success">글쓰기</a>
		</div>
		</c:if>
		<%-- <table class="table table-bordered table-hover" 
			style="width: 1000px; text-align: center; border: 0px;">
			<c:forEach var="b" items="${list}" begin="1" varStatus="status">
			<c:if test="${status.index%5 == 0}"><tr></c:if>
				
					<td style="line-height: 70px;  border-left: 0px; border-right: 0px">
					<div style="width: 200px;">
						<a href="imageBoardDetail?bno=${b.bno}">
							<img src="imageBoardImage?bno=${b.bno }" width="200px" height="200px"/>
							<span class="glyphicon glyphicon glyphicon-eye-open input-group"> ${b.btitle}</span>

							<c:set var="now" value="<%= new java.util.Date() %>"/> 
							<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd"/>
							<c:if test="${b.bdate == today}"><img src="../resources/image/new03.gif" style="width: 30px"/></c:if>
							<c:if test="${b.blikecount >= 10}"><img src="../resources/image/hot01.gif" style="width: 30px"/></c:if>
						</a>					
						<div class="glyphicon glyphicon glyphicon-eye-open input-group"> ${b.bhitcount}</div>
					</div>
					</td>
			<c:if test="${status.index%5 == 0}"></tr></c:if>
				
			</c:forEach> --%>

	</div>
</body>
</html>