package com.mycompany.myapp.websocket;

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
public class DistributorHandler extends TextWebSocketHandler implements ApplicationListener{
	//Field
	private List<WebSocketSession> list = new Vector<>();
	private String url = "tcp://106.253.56.122:1883";
	private String clientId ="distributor";
	private String deviceRequest = "/devices/+/request";
	private String deviceResponse = "/gyro/response";
	private int qos = 1;
	private MqttClient mqttClient;

	private MqttCallback callback = new MqttCallback() {
		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {
		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
			publish(mm.toString());
		}

		@Override
		public void connectionLost(Throwable thrwbl) {
			try {
				close();
			} catch (MqttException ex) {
				ex.printStackTrace();
			}
		}
	};
	
	//Constructor
	public DistributorHandler() throws MqttException {
		mqttClient = new MqttClient(url, clientId);
		mqttClient.setCallback(callback);
		mqttClient.connect();
	}
	
	//Method
	public void subscribe() throws MqttException {
		mqttClient.subscribe(deviceRequest);
	}
	
	public void publish(String json) throws MqttException {
		JSONObject jsonObject = new JSONObject(json);
		String to = jsonObject.getString("to");
		double yawAngle = Double.parseDouble(jsonObject.getString("yawAngle"));
		double pitchAngle = Double.parseDouble(jsonObject.getString("pitchAngle"));
		double rollAngle = Double.parseDouble(jsonObject.getString("rollAngle"));
		jsonObject.put("time", getUTCTime(new Date().getTime()));
		jsonObject.put("yawAngle", yawAngle);
		jsonObject.put("pitchAngle", pitchAngle);
		jsonObject.put("rollAngle", rollAngle);
		json = jsonObject.toString();
		try {
			for(WebSocketSession session:list){				
				session.sendMessage(new TextMessage(json));				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String targetResponse = String.format(deviceResponse, to);
		MqttMessage mqttMessage = new MqttMessage(json.getBytes());
		mqttMessage.setQos(qos);
		mqttClient.publish(targetResponse, mqttMessage);
	}
	
	public long getUTCTime(long localTime){
		long utcTime = 0;
		TimeZone tz = TimeZone.getDefault();
		try{
			int offset = tz.getOffset(localTime);
			utcTime = localTime + offset;
		}catch(Exception e){}
		return utcTime;
	}
	
	public void close() throws MqttException {
		if(mqttClient != null) {
			mqttClient.disconnect();
			mqttClient.close();
			mqttClient = null;
		}
	}

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

