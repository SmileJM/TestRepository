<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>JSP Page</title>
<!-- start: CSS -->
<link id="bootstrap-style" href="<%=application.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=application.getContextPath()%>/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="<%=application.getContextPath()%>/resources/css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="<%=application.getContextPath()%>/resources/css/style-responsive.css" rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'
>
<!-- end: CSS -->

<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<style>
body {
	color: #cccccc;
	font-family: Monospace;
	font-size: 13px;
	text-align: center;
	background-color: #000000;
	margin: 0px;
	overflow: hidden;
}
</style>
<!--  하이차트 start -->
<script src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts.js"></script>
<script src="<%=application.getContextPath()%>/resources/highcharts/code/themes/gray.js"></script>
<script src="<%=application.getContextPath()%>/resources/highcharts/code/highcharts-more.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/ultrasonicChart.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/ifraredrayChart.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/gyroChart.js"></script>
<!-- 하이차트 end -->
<script>
		function handleIP(){
			if( $("#ip").val()=="") {
				$("#ip").attr("placeholder", "CoAP server IP");
				$("#ip").focus();
				return ;
			}
			return $("#form").submit();
		}
</script>

</head>
<body>

	<jsp:include page="home.jsp" flush="false"></jsp:include>
		
	<form method="post" id="form" style="height: 40px;">
		<input type="text" id="ip" placeholder="CoAP server IP"	name="ip"  style="width: 200px; height: 40px; margin-top:10px"  maxlength="15"/>
		<input type="button" class="btn btn-success" value="IP설정"  style="margin-top:2px" onclick="handleIP()" />
		<input type="text" id="ipDisplay" value="${ip}" style="width: 200px; height: 40px; margin-top:10px" readOnly />
	</form>
	<div  style="width: 100%; height: 10%">
		<div style="width: 30%; float: right">
			<div id="gyroChartContainer" ></div>
			<div id="ultrasonicChartContainer"></div>
			<div id="ifraredrayChartContainer"></div>
		</div>
		<div style="width: 70%; float: left" id="container"></div>
		
	</div>



	<script src="<%=application.getContextPath()%>/resources/js/threejs/libs/dat.gui.min.js"></script>
	<script src="<%=application.getContextPath()%>/resources/js/threejs/three.js"></script>
	<script src="<%=application.getContextPath()%>/resources/js/threejs/controls/OrbitControls.js"></script>
	<script src="<%=application.getContextPath()%>/resources/js/threejs/libs/stats.min.js"></script>

	<script>
			var group = new Array();
			var camera, scene, renderer;
			var positions, colors;
			var maxParticleCount = 1000;
			var particleCount = 500;
			var r = 800;
			var rHalf = r / 2;
			var yawAngle=0, pitchAngle=0, rollAngle=0, preyawAngle=0, prepitchAngle=0, prerollAngle=0;
			init();
			requestGyroSensorData();
			animate();

			function init() {

				container = document.getElementById( 'container' );
				//
				camera = new THREE.PerspectiveCamera( 45, window.innerWidth / window.innerHeight, 1, 4000 );
				camera.position.z = 1750;
				camera.position.set( 1500, 450, 0 );
				controls = new THREE.OrbitControls( camera, container );
				scene = new THREE.Scene();
				group = new THREE.Group();
				scene.add( group );
				scene.add( new THREE.AxisHelper( 1000 ) );
				scene.position.z = 300;
				
				var mesh = new THREE.Mesh( new THREE.BoxGeometry( r, 0.05*r, 0.7*r ) );				
				var helper = new THREE.BoxHelper( mesh );
				helper.material.color.setHex( 0xffffff );
				helper.material.blending = THREE.AdditiveBlending;
				helper.material.transparent = true;
				group.add( helper );
				
								
				
				var material = new THREE.LineBasicMaterial({
					color: 0x191919
				});
				
				var material1 = new THREE.LineBasicMaterial({
					color: 0x020202
				});
				
				var material2 = new THREE.LineBasicMaterial({
					color: 0x771177
				});
				
				for(var i=1;i<15;i++){
					var geometry = new THREE.Geometry();
					geometry.vertices.push(
							
						new THREE.Vector3( 400, 20, 280-40*i),
						new THREE.Vector3( -400, 20, 280-40*i)					
						
					);

					var line = new THREE.Line( geometry, material );
					group.add( line );
				}
				
				for(var i=1;i<21;i++){
					var geometry = new THREE.Geometry();
					geometry.vertices.push(
							
						new THREE.Vector3( 400-40*i, 20, 280),
						new THREE.Vector3( 400-40*i, 20, -280)
						
					);
					
					if(i>12) var line = new THREE.Line( geometry, material2 );
					else var line = new THREE.Line( geometry, material );
					group.add( line );
				}
				
				for(var i=1;i<15;i++){
					var geometry = new THREE.Geometry();
					geometry.vertices.push(
							
						new THREE.Vector3( 400, -20, 280-40*i),
						new THREE.Vector3( -400, -20, 280-40*i)					
						
					);
	
					var line = new THREE.Line( geometry, material1 );
					group.add( line );
				}
				for(var i=1;i<21;i++){
					var geometry = new THREE.Geometry();
					geometry.vertices.push(
							
						new THREE.Vector3( 400-40*i, -20, 280),
						new THREE.Vector3( 400-40*i, -20, -280)
						
					);
	
					var line = new THREE.Line( geometry, material1 );
					group.add( line );
				}
				
				
				var segments = maxParticleCount * maxParticleCount;
				positions = new Float32Array( segments * 3 );
				colors = new Float32Array( segments * 3 );
				
				renderer = new THREE.WebGLRenderer( { antialias: true } );
				renderer.setPixelRatio( window.devicePixelRatio );
				renderer.setSize( window.innerWidth, window.innerHeight );
				renderer.gammaInput = true;
				renderer.gammaOutput = true;
				renderer.getMaxAnisotropy();
				container.appendChild( renderer.domElement );
				
				//
				window.addEventListener( 'resize', onWindowResize, false );
			}
			function onWindowResize() {
				w=container.innerWidth;
				h=container.innerHeight;
				camera.left = w / - 2 * viewSize;
			    camera.right = w / 2 * viewSize;
			    camera.top = h / 2 * viewSize;
			    camera.bottom = h / - 2 * viewSize;
			    camera.updateProjectionMatrix();
			    renderer.setSize( w, h );
			    
				/*camera.aspect = container.innerWidth / container.innerHeight;
				camera.updateProjectionMatrix();
				renderer.setSize( window.innerWidth, window.innerHeight );*/
			}
			function animate() {
				requestAnimationFrame( animate );
				group.rotation.x = prepitchAngle/1000; // 빨강 y값 
				group.rotation.y = preyawAngle/1000; //초록 z값
				group.rotation.z = prerollAngle/1000; //파랑 x값
				
				var time = Date.now() * 0.001;
				//group.rotation.z = time * 1;
				render();
				window.addEventListener( 'resize', onWindowResize, false );
			}
			function render() {
				renderer.render( scene, camera );
			}
			
			function requestGyroSensorData(){
				var ws = new WebSocket("ws://"+location.host+"/iot1_motion/websocket/GyroSensor");
				ws.onmessage = function(event){
					var data = JSON.parse(event.data);
					preyawAngle = data.yawAngle-180;
					prepitchAngle = data.pitchAngle-180;
					prerollAngle = data.rollAngle-180;
					console.log("ddd   "+preyawAngle+"  "+prepitchAngle+"  "+prerollAngle);
					
					
					yawAngle = preyawAngle;
					pitchAngle = prepitchAngle;
					rollAngle = prerollAngle;
				};
								
			}
		</script>
</body>
</html>