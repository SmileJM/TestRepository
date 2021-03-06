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
	function writeChk() {
		if( $("#btitle").val() == "") {
			$("#btitle").attr("placeholder", "제목을 입력하세요!");
			$("#btitle").focus();
			return ;
		} else if( $("#btitle").val().length > 50) {
			$("#btitle").val("");
			$("#btitle").attr("placeholder", "50글자를 넘을 수 없습니다!");
			$("#btitle").focus();
			return ;
		} else if( $("#bwriter").val() == "") {
			$("#bwriter").attr("placeholder", "작성자를 입력하세요!");
			$("#bwriter").focus();
			return ;
		} else if( $("#bpassword").val() == "") {
			$("#bpassword").attr("placeholder", "비밀번호를 입력하세요!");	
			$("#bpassword").focus();
			return ;
		} else if( $("#battach").val() == "") {
			$("#battach").attr("placeholder", "이미지를 올려주세요!");
			$("#battach").focus();
			return ;
		} 
		return $("#form1").submit();
	}
	</script>
</head>
<body>
	<div style="max-width: 1000px; margin: auto;">
	<h4>게시물 등록</h4>
	<hr />
	<form method="post" action="exam07Write?type=${param.type }" style="padding: 0px 20px"
		enctype="multipart/form-data" id="form1">
		<input type="hidden"  name="type" value="${param.type}"/>
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-pencil"></span>
				</span> <input type="text" class="form-control" placeholder="제목"
					name="btitle"  id="btitle" maxlength="30"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-user"></span>
				</span> <input type="text" class="form-control" placeholder="작성자"
					name="bwriter" id="bwriter" maxlength="8"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-lock"></span>
				</span> <input type="password" class="form-control" placeholder="비밀번호"
					name="bpassword" id="bpassword"  maxlength="10"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-pencil"></span>
				</span>
				<textArea rows="10" cols="30" class="form-control" placeholder="내용" 
					name="bcontent"></textArea>
			</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-picture"></span>
				</span> <input type="file" class="form-control" placeholder="선택"
					name="battach" id="battach"/>
			</div>
		</div>
		<div align="right">
		<a class="btn btn-success" href="exam07List?type=${param.type }" >취소</a>
		<input type="button" class="btn btn-info" value="등록"  onclick="writeChk()"/>
		</div>
	</form>
	</div>
</body>
</html>