<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
		<title>Home</title>
		<link href="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%= application.getContextPath() %>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%= application.getContextPath() %>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script>
			function handleBtnUltrasonicSensor() {
				var angle = $("#txtUltrasonicSensorAngle").val();
				console.log(angle);
				$.ajax({
					url:"ultrasonicsensor",
					data: {"angle":angle},
					success: function(data) {
						$("#spanUltrasonicSensor").text(data.distance);
					}
				})
			}
		</script>
	</head>

	<body>
		<h3>SensingCar Web Control</h3>
		<hr/>
		<h4>[UltrasonicSensor 제어]</h4>
		<input id="txtUltrasonicSensorAngle" type="number" min="0" max="180" value="90"/>
		<button id="btnUltrasonicSensor" class="btn btn-info" onclick="handleBtnUltrasonicSensor()">거리측정</button>
		<span id="spanUltrasonicSensor"></span>cm
	</body>
</html>
