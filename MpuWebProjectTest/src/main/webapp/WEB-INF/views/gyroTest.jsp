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
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<style>
			body {
				font-family: Monospace;
				background-color: #000;
				margin: 0px;
				overflow: hidden;
			}
			#info {
				position: absolute;
				color: #fff;
				top: 0px;
				width: 100%;
				padding: 5px;
				text-align:center;
			}
			a {
				color: #fff;
			}
		</style>
	</head>
<body>
<div id="info"><a href="http://threejs.org" target="_blank" rel="noopener">three.js</a> - ConvexGeometry</div>

		<script src="<%= application.getContextPath() %>/resources/js/threejs/three.js"></script>
		<script src="<%= application.getContextPath() %>/resources/js/threejs/controls/OrbitControls.js"></script>
		<script src="<%= application.getContextPath() %>/resources/js/threejs/QuickHull.js"></script>
		<script src="<%= application.getContextPath() %>/resources/js/threejs/geometries/ConvexGeometry.js"></script>
		<script src="<%= application.getContextPath() %>/resources/js/threejs/Detector.js"></script>
		<script src="<%= application.getContextPath() %>/resources/js/threejs/libs/stats.min.js"></script>

		<script>
			if ( ! Detector.webgl ) Detector.addGetWebGLMessage();
			var group, camera, scene, renderer;
			init();
			animate();
			function init() {
				scene = new THREE.Scene();
				renderer = new THREE.WebGLRenderer( { antialias: true } );
				renderer.setPixelRatio( window.devicePixelRatio );
				renderer.setSize( window.innerWidth, window.innerHeight );
				document.body.appendChild( renderer.domElement );
				// camera
				camera = new THREE.PerspectiveCamera( 40, window.innerWidth / window.innerHeight, 1, 1000 );
				camera.position.set( 15, 20, 30 );
				scene.add( camera );
				// controls
				var controls = new THREE.OrbitControls( camera, renderer.domElement );
				controls.minDistance = 20;
				controls.maxDistance = 80;
				controls.maxPolarAngle = Math.PI;
				scene.add( new THREE.AmbientLight( 0x222222 ) );
				// light
				var light = new THREE.PointLight( 0xffffff, 1 );
				camera.add( light );
				// helper
				scene.add( new THREE.AxisHelper( 20 ) );
				// textures
				var loader = new THREE.TextureLoader();
				var texture = loader.load( 'textures/sprites/disc.png' );
				group = new THREE.Group();
				scene.add( group );
				// points
				var pointsGeometry = new THREE.DodecahedronGeometry( 10 );
				for ( var i = 0; i < pointsGeometry.vertices.length; i ++ ) {
					//pointsGeometry.vertices[ i ].add( randomPoint().multiplyScalar( 2 ) ); // wiggle the points
				}
				var pointsMaterial = new THREE.PointsMaterial( {
					color: 0x0080ff,
					map: texture,
					size: 1,
					alphaTest: 0.5
				} );
				var points = new THREE.Points( pointsGeometry, pointsMaterial );
				group.add( points );
				// convex hull
				var meshMaterial = new THREE.MeshLambertMaterial( {
					color: 0xffffff,
					opacity: 0.5,
					transparent: true
				} );
				var meshGeometry = new THREE.ConvexBufferGeometry( pointsGeometry.vertices );
				var mesh = new THREE.Mesh( meshGeometry, meshMaterial );
				mesh.material.side = THREE.BackSide; // back faces
				mesh.renderOrder = 0;
				group.add( mesh );
				var mesh = new THREE.Mesh( meshGeometry, meshMaterial.clone() );
				mesh.material.side = THREE.FrontSide; // front faces
				mesh.renderOrder = 1;
				group.add( mesh );
				//
				window.addEventListener( 'resize', onWindowResize, false );
			}
			function randomPoint() {
				return new THREE.Vector3( THREE.Math.randFloat( - 1, 1 ), THREE.Math.randFloat( - 1, 1 ), THREE.Math.randFloat( - 1, 1 ) );
			}
			function onWindowResize() {
				camera.aspect = window.innerWidth / window.innerHeight;
				camera.updateProjectionMatrix();
				renderer.setSize( window.innerWidth, window.innerHeight );
			}
			function animate() {
				requestAnimationFrame( animate );
				group.rotation.x += 0;
				render();
			}
			function render() {
				renderer.render( scene, camera );
			}
		</script>

</body>
</html>