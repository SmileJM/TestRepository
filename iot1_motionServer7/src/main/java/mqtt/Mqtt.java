//package mqtt;
//
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//import java.util.Vector;
//
//import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
//import org.eclipse.paho.client.mqttv3.MqttCallback;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.json.JSONObject;
//import org.springframework.web.socket.WebSocketSession;
//
//import com.mycompany.myapp.dto.Member;
//import com.mycompany.myapp.websocket.GyroSensorHandler;
//
//public class Mqtt {
//
//	private List<WebSocketSession> list = new Vector<>();
//	private String myClientId;
//	private MqttClient mqttClient;
//	private String topicRequest;
//	private String topicResponse;
//	private Member member;
//	private int qos = 1;
//	
//	public Mqtt(MqttClient mqttClient) {
//		myClientId = MqttClient.generateClientId();
//		topicRequest = "/" + member.getMid() + "/gyro/request";
//		topicResponse = "/" + member.getMid() + "/gyro/response";
//
//		String url = "tcp://106.253.56.122:1883";
//		try {
//			mqttClient = new MqttClient(url, myClientId);
//			mqttClient.setCallback(callback);
//			mqttClient.connect();
//			subscribe();
//			Thread.sleep(1000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private MqttCallback callback = new MqttCallback() {
//
//		@Override
//		public void deliveryComplete(IMqttDeliveryToken imdt) {
//
//		}
//
//		@Override
//		public void messageArrived(String string, MqttMessage mm) throws Exception {
//			String json = new String(mm.getPayload());
//			JSONObject jsonObject = new JSONObject(json);
//
//			jsonObject.put("mid", member.getMid());
////			json = jsonObject.toString();
//			
//			double yawAngle = Double.parseDouble(jsonObject.getString("yawAngle"));
//			double pitchAngle = Double.parseDouble(jsonObject.getString("pitchAngle"));
//			double rollAngle = Double.parseDouble(jsonObject.getString("rollAngle"));
//			jsonObject.put("time", getUTCTime(new Date().getTime()));
//			jsonObject.put("yawAngle", yawAngle);
//			jsonObject.put("pitchAngle", pitchAngle);
//			jsonObject.put("rollAngle", rollAngle);
//			json = jsonObject.toString();			
//
////			GyroSensorHandler.getInstance().send("mid", member.getMid());
//			GyroSensorHandler.getInstance().send("json", json);		
//
//		}
//
//		@Override
//		public void connectionLost(Throwable thrwbl) {
//			try {
//				close();
//			} catch (MqttException ex) {
//				ex.printStackTrace();
//			}
//		}
//	};
//
//	public long getUTCTime(long localTime) {
//		long utcTime = 0;
//		TimeZone tz = TimeZone.getDefault();
//		try {
//			int offset = tz.getOffset(localTime);
//			utcTime = localTime + offset;
//		} catch (Exception e) {
//		}
//		return utcTime;
//	}
//
//	public void close() throws MqttException {
//		if (mqttClient != null) {
//			mqttClient.disconnect();
//			mqttClient.close();
//			mqttClient = null;
//		}
//	}
//
//	public void publish(String targetClientId, String text) throws MqttException {
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("text", text);
//		String json = jsonObject.toString();
//
//		MqttMessage mqttMessage = new MqttMessage(json.getBytes());
//		mqttMessage.setQos(qos);
//		mqttClient.publish(topicRequest, mqttMessage);
//	}
//	
//	public void subscribe() throws MqttException {
//		mqttClient.subscribe(topicResponse);
//	}
//}
