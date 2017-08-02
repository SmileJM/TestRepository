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
	<h3>Connect to Facebook</h3>
	<!-- 
	<form action="/connect/facebook" method="POST">
		<input type="hidden" name="scope" value="user_posts" />
		<div class="formInfo">
			<p>You aren't connected to Facebook yet. Click the button to
				connect this application with your Facebook account.</p>
		</div>
		<p>
			<button type="submit">Connect to Facebook</button>
		</p>
	</form>
 -->
	<form action="/connect/facebook" method="post" id="facebook-form">
		<input type="hidden" name="scope" value="public_profile, email" />
		<button type="submit">Sign In with Facebook</button>
	</form>
</body>
</html>