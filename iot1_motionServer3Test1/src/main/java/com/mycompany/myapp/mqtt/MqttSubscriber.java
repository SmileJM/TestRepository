package com.mycompany.myapp.mqtt;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MqttSubscriber  extends TextWebSocketHandler implements ApplicationListener {
	//Field
	private MqttClient mqttClient;
	private List<WebSocketSession> list = new Vector<>();
	
	private MqttCallback mqttCallback = new MqttCallback() {
		@Override
		public void connectionLost(Throwable thrwbl) {
		}
		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
			String json = new String(mm.getPayload());
			
			System.out.println("messageArrived: " + json);
			JSONObject jsonObject = new JSONObject(json);
//			String to = jsonObject.getString("to");
			double yawAngle = Double.parseDouble(jsonObject.getString("yawAngle"));
			double pitchAngle = Double.parseDouble(jsonObject.getString("pitchAngle"));
			double rollAngle = Double.parseDouble(jsonObject.getString("rollAngle"));
			jsonObject.put("time", getUTCTime(new Date().getTime()));
			jsonObject.put("yawAngle", yawAngle);
			jsonObject.put("pitchAngle", pitchAngle);
			jsonObject.put("rollAngle", rollAngle);
			json = jsonObject.toString();
			
//			
//			try {
//				for(WebSocketSession session:list){				
//					session.sendMessage(new TextMessage(json));				
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				for(WebSocketSession session:list){					
//					session.sendMessage(new TextMessage(json));					
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {
		}
	};
	public long getUTCTime(long localTime){
		long utcTime = 0;
		TimeZone tz = TimeZone.getDefault();
		try{
			int offset = tz.getOffset(localTime);
			utcTime = localTime + offset;
		}catch(Exception e){}
		return utcTime;
	}
	//Constructor
	public MqttSubscriber() throws MqttException {
		//MQTT 클라이언트 생성
		mqttClient = new MqttClient("tcp://106.253.56.122:1883", MqttClient.generateClientId());
		//통신 결과에 따라 콜백 메소드를 실행할 콜백 객체 생성
		mqttClient.setCallback(mqttCallback);
		//MQTT 브로커와 연결하기
		mqttClient.connect();
	}

	//Method
	public void subscribe() throws MqttException {
		//지정한 톱픽의 구독자로 신청
		mqttClient.subscribe("/devices/device1/temperature");
//		mqttClient.subscribe("/gyro/response");
	}
	
	public void shutdown() throws MqttException {
		//MQTT 브로커와 연결 끊기
		mqttClient.disconnect();
		//MqttClient가 사용한 리소스(메모리)를 해제
		mqttClient.close();
	}
	
	public static void main(String[] args) throws Exception {
		MqttSubscriber subscriber = new MqttSubscriber();
		//구독자로 신청
		subscriber.subscribe();
		System.out.println("start subscriber....");
		
		//프로그램이 종료되지 않게 멈춤
		System.out.println("press any key to quit");
		System.in.read();
		
		//클라이언트 종료
		subscriber.shutdown();
	}
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
