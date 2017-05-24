<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>JSP Page</title>
		<link href="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%= application.getContextPath() %>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script>
			$(function () {
				var img = $("#img1");
				img.attr("src", "<%= application.getContextPath() %>/resources/image/photo01.jpg");
			});

			$(document).ready(function () {
				console.log("log1");
			});

			$(function () {
				console.log("log2");
			});
		</script>
	</head>
	<body>
		<h1>로드 완료 함수</h1>
		<img id="img1" width="100px" height="100px"/>		
	</body>
</html>
