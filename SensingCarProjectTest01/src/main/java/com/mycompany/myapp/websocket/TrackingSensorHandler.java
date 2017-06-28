package com.mycompany.myapp.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.annotation.PostConstruct;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
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

@Component
public class TrackingSensorHandler extends TextWebSocketHandler implements ApplicationListener{
	private static final Logger logger = LoggerFactory.getLogger(TrackingSensorHandler.class);
	private List<WebSocketSession> list = new Vector();
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;

	@PostConstruct
	public void init() {
		coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.50/trackingsensor");
		coapObserveRelation = coapClient.observe(new CoapHandler() {

			@Override
			public void onLoad(CoapResponse response) {
				String json = response.getResponseText();
				JSONObject jsonObject = new JSONObject(json);
				String color = jsonObject.getString("color");
				jsonObject = new JSONObject();
				jsonObject.put("time", getUTCTime(new Date().getTime()));
				jsonObject.put("color", color);
				json = jsonObject.toString();
				try {
					for (WebSocketSession session : list) {
						session.sendMessage(new TextMessage(json));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onError() {

			}
		});
	}

	// 연결이 되었을 경우 자동 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("");
		// 연결시 list 에 세션 저장
		list.add(session);
	}

	// 메시지를 닫은 경우 자동 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("");
	}

	// 연결이 끊긴경우 자동실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("");
		list.remove(session);
	}

	// 로컬 타임을 UTC 타임으로 바꾸기
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

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContextClosedEvent ) {
			logger.info("웹 애플리케이션 종료");
			coapObserveRelation.proactiveCancel();
			coapClient.shutdown();		
		}		
	}


}
