package com.mycompany.myapp.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EchoHandler extends TextWebSocketHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(EchoHandler.class);

	// 연결이 되었을 경우 자동 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.info("");
	}

	// 메시지를 닫은 경우 자동 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.info("");
		String strMessage = message.getPayload();
		// WebSocketMessage를 구현한 객체 생성
		TextMessage textMessage = new TextMessage(strMessage);
		session.sendMessage(textMessage);
	}

	// 연결이 끊긴경우 자동실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("");
	}
}
