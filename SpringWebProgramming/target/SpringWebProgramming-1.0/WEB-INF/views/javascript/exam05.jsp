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
			var v1 = "abc";
			console.log(v1.length);

			var v2 = "JavaScript is easy";
			console.log(v2.indexOf("easy2"));

			var v3 = "123456-1234567";
			console.log(v3.substr(7, 7));
			console.log(v3.substr(7));
			console.log(v3.charAt(7));

			var v4 = "10:20:30";
			var array = v4.split(":");
			console.log(array[0]);
			console.log(array[1]);
			console.log(array[2]);
		</script>			
	</head>
	<body>
		<h1>String의 메소드</h1>
	</body>
</html>
