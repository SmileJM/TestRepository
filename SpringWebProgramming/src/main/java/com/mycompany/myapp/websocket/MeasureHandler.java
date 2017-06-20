package com.mycompany.myapp.websocket;

import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MeasureHandler extends TextWebSocketHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(MeasureHandler.class);
	private List<WebSocketSession> list = new Vector();

	@PostConstruct
	public void init() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				int temperature = 0;
				while (true) {
					try {
						for (WebSocketSession session : list) {
							session.sendMessage(new TextMessage(String.valueOf(++temperature)));
						}
						Thread.sleep(1000);
					} catch (Exception e) {
					}
				}
			}
		};
		thread.start();
	}

	// 연결이 되었을 경우 자동 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.info("");
		// 연결시 list 에 세션 저장
		list.add(session);
	}

	// 메시지를 닫은 경우 자동 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.info("");
	}

	// 연결이 끊긴경우 자동실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("");
		list.remove(session);
	}
}
