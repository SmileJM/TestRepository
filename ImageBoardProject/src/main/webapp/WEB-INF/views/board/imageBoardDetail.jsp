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
	<script>
		function handleBtnUpdate() {
			var bpassword = $("#bpassword").val();
			if( bpassword =="") {
				$("#bpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
				$("#bpassword").focus();
				return ;
			}
			$.ajax({
				url: "imageBoardCheckBpassword",
				method: "post",
				// data: "bno=${board.bno}&bpassword="+bpassword,
				data: {"bno":"${board.bno}", "bpassword":bpassword},
				success: function(data) {
					if(data.result =="success") {
						// console.log("success");
						location.href="imageBoardUpdate?type=${param.type }&bno=${board.bno}";
					} else {
						$("#bpassword").val("");
						$("#bpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
						$("#bpassword").focus();
					}
				}
			});
		}
		function handleBtnDelete() {
			var bpassword = $("#bpassword").val();
			if( bpassword =="") {
				$("#bpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
				$("#bpassword").focus();
				return ;
			}
			$.ajax({	
				url: "imageBoardCheckBpassword",
				method: "post",
				data: {"bno":"${board.bno}", "bpassword":bpassword},
				success: function(data) {
					if(data.result =="success") {
						console.log("success");
						location.href="imageBoardDelete?type=${param.type }&bno=${board.bno}";
					} else {
						$("#bpassword").val("");
						$("#bpassword").attr("placeholder", "비밀번호를 입력하셔야 합니다.");
						$("#bpassword").focus();
					}
				}
			});
		}
		function handleBtnLike() {
			location.href="imageBoardLike?type=${param.type }&bno=${board.bno}";		
		}
	</script>
</head>
<body>
	<div style="max-width: 1000px; margin: auto;">
	<h4>${board.btitle}</h4>
	<hr />
	<form method="post"  style="padding: 0px 20px"
		enctype="multipart/form-data">
		<input type="hidden"  name="type" value="${param.type}"/>
		<div class="form-group">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="번호"	name="bno"  value="${board.bno}" style="background-color: white; width:50px;]" readonly/>
				<span class="input-group-addon"> <span class="glyphicon glyphicon-user"></span></span>
				<input type="text" class="form-control" placeholder="작성자"	name="bwriter"  value="${board.bwriter}" style="background-color: white;" readonly/>
				<span class="input-group-addon"> <span class="glyphicon glyphicon-heart"></span></span>
				<input type="text" class="form-control" placeholder="추천수"	name="blikecount"  value="${board.blikecount}"  style="background-color: white;" readonly/>
				<span class="input-group-addon"> <span class="glyphicon glyphicon-eye-open"></span></span>
				<input type="text" class="form-control" placeholder="조회수"	name="bhitcount"  value="${board.bhitcount}"  style="background-color: white;" readonly/>
				<span class="input-group-addon"> <span class="glyphicon glyphicon-time"></span></span>
				<input type="text" class="form-control" placeholder="날짜"	 name="bdate"  value="${board.bdate}" style="background-color: white;" readonly/>
			</div>
		</div>

		<div class="form-group" style="text-align: center;">
			<img src="imageBoardImage?bno=${board.bno }" style="max-width: 980px;"/>
		</div>
		<div class="form-group" style="text-align: center;">
			<%-- <textArea rows="10" cols="30" class="form-control" placeholder="내용"  name="bcontent" readonly="readonly" style="background-color: white; border: 0px;">${board.bcontent }</textArea> --%>
			<p>${board.bcontent }</p>
		</div>
		<div class="form-group" style="text-align: center;">
			<div class="btn btn-danger" style="border-radius: 50px; width: 100px; height: 100px; line-height: 30px;" onclick="handleBtnLike()">
				${board.blikecount}<br/>
				<img src="../resources/image/thumbsup01.png" style="width: 30px; height: 30px;"/>
			</div>
			
		</div>
		<div class="form-group" align="right">
			<input type="password" id="bpassword" placeholder="비밀번호"	name="bpassword"  style="width: 150px; height: 33px;"  maxlength="10"/>
			<a href="imageBoardList?type=${param.type }" class="btn btn-primary" >목록</a>
			<input type="button" class="btn btn-warning" value="수정"  onclick="handleBtnUpdate()" />
			<input type="button" class="btn btn-danger" value="삭제"    onclick="handleBtnDelete()"/>			
		</div>		
	</form>
	</div>
</body>
</html>