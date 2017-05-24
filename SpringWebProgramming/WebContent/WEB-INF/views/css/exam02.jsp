<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP Page</title>
		<style type="text/css">
			/*태그 선택자(selector), div 가 선택자임*/
			div {
				background-color: yellow; border:1px solid black; height: 50px;
			}
			/*class 선택자*/
			.skyblue{
				background-color: skyblue;
			}
			.btn{
				border: 1px solid black;
				height: 70px;
				padding: 5px;				
			}
			.success {
				background-color: forestgreen;
				color: white;
			}
			.warining {
				background-color: red;
				color: white;
			}
			/*id 선택자*/
			#goldDiv1 {
				background-color: gold;
			}
			#goldDiv2 {
				background-color: goldenrod;
			}
		</style>
	</head>
	<body>
		<div >div 태그</div>
		<div >div 태그</div>
		<div >div 태그</div>
		<div class="skyblue">div 태그</div>
		<div class="skyblue">div 태그</div>
		<div id="goldDiv1" class="skyblue">div 태그</div>
		<div id="goldDiv2" class="skyblue">div 태그</div>
		<a class="btn success">확인</a>
		<a class="btn warining">확인</a>
	</body>
</html>
