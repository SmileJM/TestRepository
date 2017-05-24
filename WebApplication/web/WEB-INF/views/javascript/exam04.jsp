<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>javaScript</title>
		<link href="/WebApplication/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="/WebApplication/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="/WebApplication/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="/WebApplication/resources/js/exam04.js" type="text/javascript"></script>
		<script>
			var result = totalSum(1, 100);
			console.log("result:" + result);

			var result2 = totalSum(100);
			console.log("result2:" + result2);
		</script>
	</head>
	<body>
		<button onclick="handleBtnOk()" >OK</button>		
		<a href="javascript:handleBtnOk()"  >OK</a>
	</body>
</html>
