<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>Home</title>
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
	<h4>WebApplication Home</h4>
	<hr>
	

	<nav>
		<p sec:authentication="name">Username</p>
		<a th:href="@{/logout}">Logout</a>
	</nav>

	<h1>
		Welcome, <span sec:authentication="name">Username</span>
	</h1>
	<p sec:authentication="authorities">User authorities</p>
</body>
</html>
