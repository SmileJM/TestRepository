<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
 
<jsp:include page="home.jsp" flush="false"></jsp:include>
<video id="video" src="<%=application.getContextPath()%>/resources/img/video.mp4" controls autoplay >HTML5 Video is required for this example</video>
	<script type="text/javascript">
	var videocontrol = document.getElementById("video");
	var motion;
	function motionControl(){
		var ws = new WebSocket("ws://"+location.host+"/iot1_motion/websocket/GyroSensor");
		ws.onmessage = function(event){
			var data = JSON.parse(event.data);
			motion = data.motion;
			console.log("ddd   "+motion);	
		};
		if(motion=="zigzag") videocontrol.play();
		else if(motion=="pitchRightRotation") videocontrol.pause();
	}
	
	</script>
</body>
</html>