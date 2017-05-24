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
			var v1 = "100.55";
			var v2 = v1 + 200;
			console.log(v2);
			var v3 = parseInt(v1) + 200;
			var v4 = parseFloat(v1) + 200;
			console.log(v3);
			console.log(v4);			
			var v5 = String(10) + "점";
		</script>
	</head>
	<body>
		<h1>글로벌 함수</h1>
	</body>
</html>
