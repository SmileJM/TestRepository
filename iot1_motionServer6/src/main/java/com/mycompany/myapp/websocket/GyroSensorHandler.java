package com.mycompany.myapp.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;

import javax.annotation.PostConstruct;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.mycompany.myapp.controller.HomeController;

@Component
public class GyroSensorHandler extends TextWebSocketHandler implements ApplicationListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(GyroSensorHandler.class);
	private static GyroSensorHandler singleton = new GyroSensorHandler();

	private GyroSensorHandler() {

	}

	public static GyroSensorHandler getInstance() {
		return singleton;
	}

	Map<String, WebSocketSession> map =  new HashMap<String, WebSocketSession>();
	
	private List<WebSocketSession> list = new Vector<>();

	private String url = "tcp://106.253.56.122:1883";
	private String myClientId;
	private String topicRequest;
	private String topicResponse;

	private int qos = 1;
	private MqttClient mqttClient;

	private String mid;	
	private String yawAngle;
	private String pitchAngle;
	private String rollAngle;
	private String json;

	@PostConstruct
	public void init() throws MqttException {		
		System.out.println("init");
		
//		Thread thread = new Thread(){
//			@Override
//			public void run() {
//				while(true) {			
//					try {
//						for (WebSocketSession session : list) {
//							session.sendMessage(new TextMessage(json));
//						}
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		};
//		thread.start();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.info("");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("");
		list.remove(session);
	}
	

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
	}

	public void send(String data, String json) {		
		System.out.println("json: " + json);
		this.json = json;	
		try {
			for (WebSocketSession session : list) {
				System.out.println("session: " + session);
				session.sendMessage(new TextMessage(json));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("mid: " + mid);
//		System.out.println("value: " + value);
	}
	public long getUTCTime(long localTime) {
		long utcTime = 0;
		TimeZone tz = TimeZone.getDefault();
		try {
			int offset = tz.getOffset(localTime);
			utcTime = localTime + offset;
		} catch (Exception e) {
		}
		return utcTime;
	}
}
