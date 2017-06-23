var trackingsensorchart;
/* 자동실행되도록 하기 위해서 $(function() {}); 사용 */
$(function() {
	/* 생성자에 객체를 매개값으로 전달 */
	trackingSensorChart = new Highcharts.Chart({
		/* chart 는 필드 */
		chart : {
			/* render > 그린다는 것(어디에 차트를 그릴 것인가) */
			renderTo : "trackingSensorChartContainer",
			/* 기본 그래프 타입이 라인그래프라는 것 */
			type : "spline",
			/* 그리기 위한 데이터를 누가 제공할 것인가 */
			events : {
				/* load 이벤트 발생시 실행, 로드 되었을 때 */
				/* requestThermistorSensorData 는 함수 */
				load : requestTrackingSensorData
			}
		},
		/* 라인마다 색을 다르게 하기 위해서 순서에 맞게 배열로 값을 지정 */
		colors : [ 'white' ],
		title : {
			text : "TrackingSensor"
		},
		/* xAxis 와 yAxis 를 정의해줌 */
		xAxis : {
			type : "datetime",
			/* 칸당 간격 */
			tickPixelInterval : 100,
			minRange : 20*1000
		},
		yAxis : {
//			minPadding : 0.2,
//			maxPadding : 0.2,
			title : {
				text : "검출",
				margin : 30
			}
		},
		/* series 는 그래프 하나당 */
		series : [ {
			name : "검출",
			data : []
		} ]
	});
});

function requestTrackingSensorData() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControl/websocket/trackingsensor");
	/* ws.onmessage > 메시지 수신시 function() {} 이 자동 실행됨 */
	ws.onmessage = function(event) {
		/* JSON.parse(event.data) 문자열로 되어 있는 JSON을 javascript 객체로 만들어 주는 작업 */
		/* {"key":"value"} */
		/* 웹소캣으로 받는 것은 문자열인데 문자열을 JSON으로 파싱하는 것 */
		var data = JSON.parse(event.data);
		/* chart 의 0번째 를 얻어 오라는 것 */
		var series = trackingSensorChart.series[0];
		/*항상 20개의 x값만 나오도록 / 20개가 넘어가면 쉬프트가 되어 20개만 보이도록*/
		var shift = series.data.length > 20;
		/*addPoint > 점에 대한 정보 x, y 축의 값 / shift 이동 > 데이터 추가 작업*/
		if(data.color == "white"){
			color = 1;
		} else {
			color = 0;
		}		
		series.addPoint([data.time, color], true, shift);
	};
}