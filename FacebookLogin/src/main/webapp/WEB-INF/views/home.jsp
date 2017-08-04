<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.myapp.social.facebook.FBConnection"%>
<%
	FBConnection fbConnection = new FBConnection();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>Home</title>
		<link href="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%= application.getContextPath() %>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	</head>

<body style="text-align: center; margin: 0 auto;">
	<div
		style="margin: 0 auto; background-image: url(<%= application.getContextPath() %>/resources/image/fbloginbckgrnd.jpg); height: 360px; width: 610px;">
		<a href="<%=fbConnection.getFBAuthUrl()%>"> <img
			style="margin-top: 138px;" src="<%= application.getContextPath() %>/resources/image/facebookloginbutton.png" />
		</a>
	</div>
</body>
</html>
