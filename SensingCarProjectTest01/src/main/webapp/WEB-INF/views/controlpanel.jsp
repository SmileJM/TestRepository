<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>WebApplication</title>
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts.js"></script>
		<script src="<%=application.getContextPath()%>/resources/highcharts/code/themes/gray.js"></script>  
		<style>@media screen and (min-width: 480px) { #title { height: 10px; } }</style>		

		<script src="<%=application.getContextPath()%>/resources/js/camera.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/rgbled.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/laseremitter.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/buzzer.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/ultrasonicsensor.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/lcd.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/fronttire.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/backtire.js"></script>
		
		<script src="<%=application.getContextPath()%>/resources/js/thermistorsensorchart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/ultrasonicsensorchart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/trackingsensorchart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/photoresistorsensorchart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/gassensorchart.js"></script>
	</head>

	<body style="background-color: black;">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-8">
					<div id="title" style="height:50px; margin:20px 0px; color: white; text-align: center; font-size: 30px; font-style: italic; font-weight: bold;">
						IoT Smart Sensing Car Control Panel
					</div>
				</div>
				<div class="col-lg-4">
					<div style="height:50px; margin-top: 40px; text-align: center; color: white; font-size: 20px; font-style: italic; font-weight: bold;">
						Powered By <span style="color: orange;">CoAP</span> Java Application
					</div>
				</div>                
			</div>
			<div class="row">
				<div class="col-lg-8">
					<div class="row">
						<div class="col-md-5">
							<div style="height:210px;">
								<img class="img-responsive"  src="<%=application.getContextPath()%>/resources/image/smartcar2.jpg" style="width:100%;height:100%;"/>
							</div>
						</div>
						<div class="col-md-3">
							<div  style="height:210px;">
								<img class="img-responsive" src="${cameraUrl}" style="width:100%; height:100%;"/>
							</div>
						</div>
						<div class="col-md-4">
							<div style="background: linear-gradient(#737373, black); color: white; height:210px; padding: 5px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">Camera 장치 제어</div>
								<div style="text-align: center">현재 상태: <span id="cameraStatus">leftright: ${leftright}º / updown: ${updown}º</span></div>
								<br/>
								<div style="text-align: center;">
									좌우<br/>
									<button id="btnLeftright180" type="button" class="btn btn-danger" onclick="camera('change', '180', '${updown}')">&lt;&lt;</button> 
									<button id="btnLeftright135" type="button" class="btn btn-warning" onclick="camera('change', '135', '${updown}')">&lt;</button> 
									<button id="btnLeftright90" type="button" class="btn btn-default" onclick="camera('change', '90', '${updown}')">정면</button>                                    
									<button id="btnLeftright45" type="button" class="btn btn-info" onclick="camera('change', '45', '${updown}')">&gt;</button>
									<button id="btnLeftright0" type="button" class="btn btn-primary" onclick="camera('change', '0', '${updown}')">&gt;&gt;</button>                                    
									<br/><br/>상하<br/>
									<button id="btnUpdown10" type="button" class="btn btn-default" onclick="camera('change', '${leftright}', '10')">정면</button>
									<button id="btnUpdown45" type="button" class="btn btn-info" onclick="camera('change', '${leftright}', '45')">45도</button>
									<button id="btnUpdown75" type="button" class="btn btn-primary" onclick="camera('change', '${leftright}', '75')">75도</button>
								</div>
							</div>
						</div>       
					</div>

					<div class="row">
						<div class="col-lg-3">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">RGBLed 장치 제어</div>
								<div style="text-align: center">현재 상태: <div id="rgbledStatus" style="width:15px;height:15px;display: inline-block; background-color: rgb(${red},${green},${blue})" ></div></div>
								<br/>
								<div style="text-align: center;">
									<button type="button" class="btn btn-danger" onclick="rgbled('change', '255', '0', '0')">Red</button>
									<button type="button" class="btn btn-success" onclick="rgbled('change', '0', '255', '0')">Green</button>
									<button type="button" class="btn btn-primary" onclick="rgbled('change', '0', '0', '255')">Blue</button>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">LaserEmitter 장치 제어</div>
								<div style="text-align: center;">현재 상태: <span id="laseremitterStatus">${laseremitterStatus }</span></div>
								<br/>
								<div style="text-align: center;">
									<button type="button" class="btn btn-warning" onclick="laseremitter('change', 'on')">ON</button>
									<button type="button" class="btn btn-info" onclick="laseremitter('change', 'off')">OFF</button>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">Buzzer 장치 제어</div>
								<div style="text-align: center">현재 상태: <span id="buzzerStatus">${buzzerStatus }</span></div>
								<br/>  
								<div style="text-align: center;">
									<button type="button" class="btn btn-warning" onclick="buzzer('change', 'on')">ON</button>
									<button type="button" class="btn btn-info" onclick="buzzer('change', 'off')">OFF</button>
								</div>                                
							</div>
						</div>      
						<div class="col-lg-3">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">UltrasonicSensor 제어</div>
								<div style="text-align: center">현재 상태: <span id="ultrasonicsensorStatus">angle: ${ultrasonicsensorAngle }º / distance: ${ultrasonicsensorDistance}cm</span></div>
								<br/>
								<div style="text-align: center;">
									<button type="button" class="btn btn-danger" onclick="ultrasonicsensor('change', '180')">&lt;&lt;</button>                       
									<button type="button" class="btn btn-default" onclick="ultrasonicsensor('change', '90')">정면</button>
									<button type="button" class="btn btn-primary" onclick="ultrasonicsensor('change', '0')">&gt;&gt;</button>
								</div>
							</div>                                    
						</div>                            
					</div>

					<div class="row">
						<div class="col-lg-4">
							<div style="background: linear-gradient(#737373, black); color: white; height:180px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">LCD 장치 제어</div>
								<div style="text-align: center">현재 상태: <span id="lcdStatus" style="text-align: left"><br/>line0: ${lcdline0} <br/>line1: ${lcdline1}</span></div>
								<br/>
								<div style="padding-left: 20px;">
									<table style="width:100%">
										<tr>
											<td style="width:70%">
												Line0: <input id="lcdline0" type="text" maxlength="16" value="This device is" style="color: black; width:120px;"/><br/>
												Line1: <input id="lcdline1" type="text" maxlength="16" value="IoT Sensing Car" style="color: black; width:120px;"/>
											</td>
											<td style="width:30%">
												<button type="button" onclick="lcd('change')" class="btn btn-primary">보내기</button>
											</td>
										</tr>
									</table>
								</div>
							</div> 
						</div>
						<div class="col-lg-3">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">FrontTire 장치 제어</div>
								<div style="text-align: center">현재 상태: <span id="fronttireStatus">angle: ${fronttireAngle}</span></div>
								<br/>
								<div style="text-align: center;">
									<button type="button" class="btn btn-danger" onclick="fronttire('change', '65')">&lt;&lt;</button>
									<button type="button" class="btn btn-default" onclick="fronttire('change', '95')">정면</button>
									<button type="button" class="btn btn-primary" onclick="fronttire('change', '125')">&gt;&gt;</button>
								</div>
							</div> 
						</div>
						<div class="col-lg-5">
							<div style="background: linear-gradient(#737373, black); color: white; height:150px; background-color: lightgray; padding: 5px; margin-top: 20px;">
								<div style="text-align: center; font-size: 18px; font-style: italic; font-weight: bold;">BackTire 장치 제어</div>
								<div style="text-align: center">현재 상태: <span id="backtireStatus" >direction: ${backtireDirection} / speed: ${backtireSpeed}</span></div>
								
								<br/>
								<div style="text-align: center;">
									<input type="hidden" value ="${backtireSpeed}" id="backtireSpeed"/>
									<input type="hidden" value ="${backtireDirection}" id="backtireDirection"/>
									<button type="button" class="btn btn-warning" onclick="backtire('change', 'forward', $('#backtireSpeed').val())" style="margin-bottom: 5px;">전진</button>
									<button type="button" class="btn btn-info" onclick="backtire('change', 'backward', $('#backtireSpeed').val())" style="margin-bottom: 5px;">후진</button>
					
									<br/>
									<button onclick="backtire('change', $('#backtireDirection').val(), '0')" style="color: black;">0</button>
									<button onclick="backtire('change', $('#backtireDirection').val(), '900')" style="color: black;">1</button>
									<button onclick="backtire('change', $('#backtireDirection').val(), '1200')" style="color: black;">2</button>
									<button onclick="backtire('change', $('#backtireDirection').val(), '1500')" style="color: black;">3</button>
									<button onclick="backtire('change', $('#backtireDirection').val(), '1800')" style="color: black;">4</button>
									<button onclick="backtire('change', $('#backtireDirection').val(), '2100')" style="color: black;">5</button>
									<button onclick="backtire('change', $('#backtireDirection').val(), '2400')" style="color: black;">6</button>
									<button onclick="backtire('change', $('#backtireDirection').val(), '2800')" style="color: black;">7</button>
									<button onclick="backtire('change', $('#backtireDirection').val(), '3200')" style="color: black;">8</button>
									<button onclick="backtire('change', $('#backtireDirection').val(), '3600')" style="color: black;">9</button>
									<button onclick="backtire('change', $('#backtireDirection').val(), '4000')" style="color: black;">10</button>
								</div>                                      
							</div> 
						</div>
					</div>          
					<div class="row">
						<div class="col-lg-6"> 
							<div id="thermistorSensorChartContainer" style="height:230px; margin-top: 20px; border:1px solid white;"></div>
						</div>
						<div class="col-lg-6"> 
							<div id="trackingSensorChartContainer" style="height:230px; margin-top: 20px; border:1px solid white;"></div>
						</div> 
					</div>
				</div>
				<div class="col-lg-4">
					<div>
						<div class="row">
							<div class="col-lg-12">
								<div id="ultrasonicSensorChartContainer" style="height:270px; border:1px solid white;"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div id="photoresistorSensorChartContainer" style="height:270px; margin-top: 15px; border:1px solid white;"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div id="gasSensorChartContainer" style="height:270px; margin-top: 15px; border:1px solid white;"></div>
							</div>
						</div>                   
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
