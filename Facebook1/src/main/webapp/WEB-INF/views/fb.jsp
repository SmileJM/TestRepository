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
	<br /> email: ${email}
	<br /> first: ${first}
	<br /> last: ${last}
	<br /> gender: ${gender}
	<br /> birthday: ${birthday}
	<br /> id: ${id}
	<br /> name: ${name}
	<%-- 	id: ${profile.id}
	<br /> username: ${profile.username}
	<br /> name: ${profile.name}
	<br /> gender: ${profile.gender}
	
	<br /> birthday: ${profile.birthday}
	<br /> hometown: ${profile.hometown}
	<br /> --%>
</body>
</html>