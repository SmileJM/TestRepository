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
		<script>
			var ws = null;
			function handleBtnConnect() {
				/* window.location.host > IP 를 동적으로 얻기 위함 */
				ws = new WebSocket("ws://" + window.location.host + "/SpringWebProgramming/websocket/echo" );
				/* onOpen > 함수 / onopen 되었을 때 handleOnOpen 을 실행해라*/
				ws.onopen = handleOnOpen;
				ws.onmessage = handleOnMessage;
				ws.onclose = handleOnClose;
				//console.log(ws);

			}
			/* ------------------------------------------------------------------------- */
			function handleOnOpen() {
				display("[연결 성공]");
				// 연결 성공시 버튼 비활성화
				$("#btnConnect").attr("disable", true);
				$("#btnDisConnect").attr("disable", false);
			}
			
			function handleOnMessage(event) {
				var strMessage = event.data;
				display("[echo]" + strMessage);
			}
			
			function handleOnClose() {
				display("[연결 해제]");
				$("#btnConnect").attr("disable", false);
				$("#btnDisConnect").attr("disable", true);
			}
			/* ------------------------------------------------------------------------- */
			function handleBtnDisConnect() {
				if(ws != null) {
					ws.close();
					/* 연결 끊긴 후 null 로 변경 */
					ws = null;
				}
			}
			function handleBtnSend() {
				var strMessage = $("#txtMessage").val();
				ws.send(strMessage);
			}
			/* ------------------------------------------------------------------------- */
			/* 메시지 라인 증가시 자동 스크롤 */
			function display(message) {
				$("#divDisplay").append("<span style='display:block;'>" + message +"</span>");
				if($("#divDisplay span").lenght >20) {
					$("#divDisplay span").first().remove();
				}
				// 스크롤이 가장 밑으로 내려오게 
				$("#divDisplay").scrollTop($("#divDisplay").height());
			}
		</script>
	</head>
	<body>
	<div style="max-width: 600px; margin: auto;">
		<h3>WebSocket-Echo</h3>
		<hr/>
		<div>
			<Button id="btnConnect" class="btn btn-warning" onclick="handleBtnConnect()">연결하기</Button>
			<Button id="btnDisConnect" class="btn btn-danger" onclick="handleBtnDisConnect()">연결끊기</Button>
		</div>
		<div>
			<input id="txtMessage" type="text">
			<Button id="btnSend" class="btn btn-info" onclick="handleBtnSend()">메시지 보내기</Button>
		</div>
		<div>
			<div id="divDisplay" style="width: 500px; height: 300px; padding: 5px; overflow-y: scroll; border: 1px solid black; margin-top: 10px"></div>
		</div>
	</div>
	</body>
</html>