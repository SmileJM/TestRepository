var sensorchart;

/* 자동실행되도록 하기 위해서 $(function() {}); 사용 */
$(function () {
	/* 생성자에 객체를 매개값으로 전달 */
	sensorChart = new Highcharts.Chart({
		/* chart 는 필드 */
		chart : {
			/* render > 그린다는 것(어디에 차트를 그릴 것인가) */
			renderTo : "sensorChartContainer",
			/* 기본 그래프 타입이 라인그래프라는 것 */
			type : "spline",
			/* 그리기 위한 데이터를 누가 제공할 것인가 */
			events : {
				/* load 이벤트 발생시 실행, 로드 되었을 때 */
				/* requestThermistorSensorData 는 함수 */
				load : requestSensorData
			}
		},
		/* 라인마다 색을 다르게 하기 위해서 순서에 맞게 배열로 값을 지정 */
		colors : [ 'red', 'yellow', 'purple'],
		title : {
			text : "Sensor Charts"
		},
		/* xAxis 와 yAxis 를 정의해줌 */
		xAxis : {
			type : "datetime",
			/* 칸당 간격 */
			tickPixelInterval : 100,
			minRange : 20 * 1000
		},
		yAxis : {
			title : {
				text : "온도(ºC)",
				margin : 30
			}
		},
		/* series 는 그래프 하나당 */
		series : [ {
			name : "온도(ºC)",
			data : []
		}, {
			name : "조도",
			type: 'column',
			data : []
		}, {
			name : "가스",
			data : []
		}]
	});
});

function requestSensorData() {
	var ws = new WebSocket("ws://" + location.host+ "/SensingCarRemoteWebControl/websocket/thermistorsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series1 = sensorChart.series[0];
		var shift = series1.data.length > 20;
		series1.addPoint([ data.time, data.temperature ], true, shift);
	};
	var ws2 = new WebSocket("ws://" + location.host	+ "/SensingCarRemoteWebControl/websocket/photoresistorsensor");	
	ws2.onmessage = function(event) {		
		var data = JSON.parse(event.data);
		var series2 = sensorChart.series[1];
		var shift = series2.data.length > 20;
		series2.addPoint([ data.time, data.photoresistor ], true, shift);		
	};
	var ws3 = new WebSocket("ws://" + location.host	+ "/SensingCarRemoteWebControl/websocket/gassensor");	
	ws3.onmessage = function(event) {		
		var data = JSON.parse(event.data);
		var series3 = sensorChart.series[2];
		var shift = series3.data.length > 20;
		series3.addPoint([ data.time, data.gas ], true, shift);		
	};
}
