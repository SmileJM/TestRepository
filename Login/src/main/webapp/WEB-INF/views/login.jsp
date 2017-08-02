<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%> 
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
	<div th:if="${param.logout}">You have been logged out</div>
	<div th:if="${param.error}">There was an error, please try again</div>

	<form th:action="@{/login}" method="POST">
		<input type="text" name="username" /> <input type="password"
			name="password" /> <input type="submit" value="Login" />
	</form>

	<form action="/signin/facebook" method="POST">
		<input type="hidden" name="scope" value="public_profile" /> <input
			type="submit" value="Login using Facebook" />
	</form>
	</body>
</html>