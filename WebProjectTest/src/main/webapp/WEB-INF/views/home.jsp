<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>Home</title>
<link
	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>

<!-- start: highchart CSS -->
<link
	href="<%=application.getContextPath()%>/resources/css/highcharts/template.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/highcharts/source-sans-pro.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/highcharts/font-awesome.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=application.getContextPath()%>/resources/css/highcharts/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<!-- end: highchart CSS -->
</head>

<body>
	<h4>WebApplication Home</h4>
	<hr>
	<div id="menu" class="nav-down">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<!-- start: Header Menu -->
				<div class="collapse navbar-collapse">
					<div id="menu-container">
						<div style="text-align: center; margin-right: 100px; float: left">
							<a style="line-height: 2"
								href="http://localhost:8080/MpuWebProject/"> IoT0619 </a>
						</div>

						<div id="menu-second" class="hidden-xs">
							<ul class="nav navbar-nav" style="line-height: 50%">
								<!-- start: User Dropdown -->
								<li class="item-112 deeper dropdown"><a
									style="width: 100px; height: 50px; vertical-align: middle;"
									class="dropdown-toggle" data-toggle="dropdown" href="#"> <img
										src="<%=application.getContextPath()%>/resources/img/student-32.png"></img>

								</a>
									<ul class="dropdown-menu" role="menu">
										<li class="item-206"><a>Account Settings</a></li>
										<li class="item-206"><c:if test="${member.memail==null}">
												<a>환영합니다.</a>
											</c:if> <c:if test="${member.memail!=null}">
												<a>${member.memail}</a>
											</c:if></li>
										<li><a href="leave"> <i class="halflings-icon off"></i>
												회원탈퇴
										</a></li>
									</ul></li>
								<!-- end: User Dropdown -->
								<div style="float: left">
									<img src="http://graph.facebook.com/${profile.getId()}/picture" />
								</div>

							</ul>
							<a class="btn btn-primary"
								href="<%=application.getContextPath()%>/fb/login"
								style="background: transparent; border: none; box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0), 0 1px 2px rgba(0, 0, 0, 0)">
								<!-- <i class="fa fa-user fa-fw"></i> --> 회원가입
							</a>
							<c:if test="${member.memail==null}">
								<a class="btn btn-primary"
									href="<%=application.getContextPath()%>/login"
									style="line-height: 30px; background: transparent; border: none; box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0), 0 1px 2px rgba(0, 0, 0, 0)">
									<!-- <i class="fa fa-user fa-fw"></i> --> 로그인
								</a>
							</c:if>
							<c:if test="${member.memail!=null}">
								<a class="btn btn-primary" href="logout"
									style="background: transparent; border: none; box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0), 0 1px 2px rgba(0, 0, 0, 0)">
									<!-- <i class="fa fa-lock"> </i> --> 로그아웃
								</a>
							</c:if>
							<div class="clearfix"></div>
						</div>
						<!-- end: Header Menu -->

						<div id="menu-main">

							<ul class="nav navbar-nav">

								<li class="item-103 deeper dropdown"><a href="#"
									class="dropdown-toggle " data-toggle="dropdown"> Demo <span
										class="toggle-arrow"></span>
								</a>
									<ul class="dropdown-menu" role="menu">
										<li class="item-106"><a href="/demo">Highcharts demos</a>
										</li>
										<li class="item-107"><a href="/stock/demo">Highstock
												demos</a></li>
										<li class="item-205"><a href="/maps/demo">Highmaps
												demo</a></li>
										<li class="item-267"><a href="/use-cases">Customer
												showcase</a></li>
									</ul></li>
								<li class="item-108 deeper dropdown"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown"> Docs <span
										class="toggle-arrow"></span>
								</a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
										<li class="item-128"><a href="/docs">General
												Documentation</a></li>
										<li class="item-124"><a
											href="http://api.highcharts.com/highcharts">Highcharts
												API Reference</a></li>
										<li class="item-125"><a
											href="http://api.highcharts.com/highstock">Highstock API
												Reference</a></li>
										<li class="item-211"><a
											href="http://api.highcharts.com/highmaps">Highmaps API
												Reference</a></li>
										<li class="item-126"><a
											href="/documentation/compatibility">Compatibility</a></li>
										<li class="item-127"><a href="/documentation/changelog">Changelog</a>
										</li>
									</ul></li>
									<li><a href="#"
									class="dropdown-toggle deeper dropdown" data-toggle="dropdown"> Docs2 <span
										class="toggle-arrow"></span></a>
										<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">				
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="#">Action</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="#">Another action</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="#">Something else here</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="#">Separated link</a></li>
									</ul>
									</li>
								<li class="item-254 deeper dropdown"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown"> Products <span
										class="toggle-arrow"></span>
								</a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="gyroTest"> <i class="icon-tasks"></i> <span
												class="hidden-tablet">Gyro Test</span>
										</a></li>
										<li class="item-256"><a href="gyroTest"> <i
												class="icon-tasks"></i> <span class="hidden-tablet">UltraSonic,
													Infrared light</span>
										</a></li>
									</ul></li>
								<li class="item-282 deeper dropdown"><a
									href="<%=application.getContextPath()%>/board/boardList"> <span
										class="hidden-tablet"> Board</span>
								</a></li>
							</ul>

							<div class="clearfix"></div>
						</div>

					</div>
				</div>
			</div>
		</nav>
	</div>

	<div style="max-width: 1000px; margin: auto;">
		<div class="form-group">
			<div class="input-group">
				<input type="text" class="form-control "  placeholder="번호"	name="bno"  value="{board.bno}" style="background-color: white; width:50px;" readonly />
				<span class="input-group-addon"> <span class="glyphicon glyphicon-user"></span></span>
				<input type="text" class="form-control" placeholder="작성자"	name="bwriter"  value="{board.bwriter}" style="background-color: white;" readonly/>
				<span class="input-group-addon"> <span class="glyphicon glyphicon-heart"></span></span>
				<input type="text" class="form-control" placeholder="추천수"	name="blikecount"  value="{board.blikecount}"  style="background-color: white;" readonly/>
				<span class="input-group-addon"> <span class="glyphicon glyphicon-eye-open"></span></span>
				<input type="text" class="form-control" placeholder="조회수"	name="bhitcount"  value="{board.bhitcount}"  style="background-color: white;" readonly/>
				<span class="input-group-addon"> <span class="glyphicon glyphicon-time"></span></span>				
				<input type="text" class="form-control" placeholder="날짜"	 name="bdate"  value="{bdate}" style="background-color: white;" readonly/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="input-group" style="border: 1px solid #D8D8D8; height:33px;">
				<input type="text" class=" "  placeholder="번호"	name="bno"  value="{board.bno}" style="background-color: white; width:50px; height: 28px; border: none;" readonly />
				<span class="input-group-addon"  style="display: inline;  border: none;"><span class="glyphicon glyphicon-user"></span></span>
				<input type="text" placeholder="작성자" name="bwriter"  value="{board.bwriter}" style="background-color: white; height: 28px; width:200px; border: none;" readonly/>
				<span class="input-group-addon" style="display: inline;  border: none;"><span class="glyphicon glyphicon-heart"></span></span>
				<input type="text" class="" placeholder="추천수"	name="blikecount"  value="{board.blikecount}"  style="background-color: white;  width:200px;height: 28px;  border: none;" readonly/>
				<span class="input-group-addon" style="display: inline;  border: none;"><span class="glyphicon glyphicon-eye-open"></span></span>
				<input type="text" class="" placeholder="조회수"	name="bhitcount"  value="{board.bhitcount}"  style="background-color: white; width:200px; height: 28px;  border: none;" readonly/>
				<span class="input-group-addon" style="display: inline;  border: none;"><span class="glyphicon glyphicon-time"></span></span>			
				<input type="text" class="" placeholder="날짜"	 name="bdate"  value="{bdate}" style="background-color: white; height: 28px;  width:190px; border: none;" readonly/>
			</div>
		</div>
		
	
				<button type="button" class="btn btn-default" name="bno"  disabled="disabled" style="width: 80px; height: 33px;">${board.bno}</button>
				<button type="button" class="btn btn-default" disabled="disabled" style="width:40px; background-color: #CEF6EC"><span class="glyphicon glyphicon-user" style="color: #FAAC58"></span></button>
				<button type="button" class="btn btn-default" name="bwriter"  disabled="disabled" style="width: 180px; height: 33px;">${board.bwriter}</button>
				<button type="button" class="btn btn-default" disabled="disabled" style="width:40px; background-color: #CEF6EC"><span class="glyphicon glyphicon-heart" style="color: #FAAC58"></span></button>
				<button type="button" class="btn btn-default" name="blikecount"  disabled="disabled" style="width: 180px; height: 33px;">${board.blikecount}</button>
				<button type="button" class="btn btn-default" disabled="disabled" style="width:40px; background-color: #CEF6EC"><span class="glyphicon glyphicon-eye-open" style="color: #FAAC58"></span></button>
				<button type="button" class="btn btn-default" name="bhitcount"  disabled="disabled" style="width: 180px; height: 33px;">${board.bhitcount}</button>
				<button type="button" class="btn btn-default" disabled="disabled" style="width:40px; background-color: #CEF6EC"><span class="glyphicon glyphicon-time" style="color: #FAAC58"></span></button>
				<button type="button" class="btn btn-default" name="bdate"  disabled="disabled" style="width: 180px; height: 33px;">${board.bdate}</button>

		
	</div>
</body>
</html>