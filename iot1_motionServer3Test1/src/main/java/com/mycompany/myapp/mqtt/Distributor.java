package com.mycompany.myapp.mqtt;

import java.util.Date;
import java.util.TimeZone;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class Distributor {
	//Field
	private String url = "tcp://106.253.56.122:1883";
	private String clientId ="distributor";
	private String deviceRequest = "/devices/+/request";
	private String deviceResponse = "/devices/%s/response";
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
	public Distributor() throws MqttException {
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
}

