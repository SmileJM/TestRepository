package mqtt;

import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.websocket.GyroSensorHandler;

@Component
public class MqttClientTest {
	private static final Logger logger = LoggerFactory.getLogger(MqttClientTest.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	String url = "tcp://106.253.56.122:1883";
	String myClientId = MqttClient.generateClientId();
	MqttClient mqttClient;
	
	
	public MqttClientTest() throws MqttException {
		mqttClient = new MqttClient(url, myClientId);
	}
	
	public void getMqtt(HttpSession session) throws MqttException{

		String mid =  (String) session.getAttribute("mid");

		mqttClient.setCallback(new MqttCallback() {
			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
				logger.info("");
			}
			@Override
			public void messageArrived(String topic, MqttMessage mm) throws Exception {	
				logger.info("");						
//				String mid = (String) session.getAttribute("mid");						
				String json = new String(mm.getPayload());						
				GyroSensorHandler gyroSensorHandler = (GyroSensorHandler) applicationContext.getBean("gyroSensorHandler");
				System.out.println("mid: " + mid);
				System.out.println("json" +  json);
//				gyroSensorHandler.sendMessage(mid, json);
			}
			@Override
			public void connectionLost(Throwable cause) {
				logger.info("");
				try {
					close();
				} catch (MqttException ex) {
					ex.printStackTrace();
				}
			}
		});
		mqttClient.connect();
		mqttClient.subscribe("/" + mid + "/gyro/response");
//		mqttClient.subscribe("/" + mid + "/#");
		session.setAttribute("mqttClient", mqttClient);
		System.out.println("나오나");
	}
	public void close() throws MqttException{
		if(mqttClient !=null){
			mqttClient.disconnect();
			mqttClient.close();
			mqttClient = null;
		}
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
