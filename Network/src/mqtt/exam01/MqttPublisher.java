package mqtt.exam01;

import java.util.Date;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublisher {
    // Field
    private MqttClient mqttClient;
    private MqttCallback mqttCallBack = new MqttCallback() {
        @Override
        public void connectionLost(Throwable thrwbl) {
            // 연결이 끊겼을 때
            System.out.println("connectionLost");
        }

        @Override
        public void messageArrived(String string, MqttMessage mm) throws Exception {
            // 구독자일 경우 메시지가 도착했을 때
            System.out.println("messageArrived");
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken imdt) {
            // publisher 이기 때문에 이 메소드만 자동으로 실행함
            System.out.println("deliveryComplete " + new Date());
        }
    };

    // Constructor
    public MqttPublisher() throws MqttException {
        // MQTT 클라이언트 생성
        // 2번째 매개변수는 보통 클라이언트가 실행되는 고객의 고유 아이디가 됨(식별아이디)
        mqttClient = new MqttClient("tcp://192.168.3.113:1883", MqttClient.generateClientId());
        // 통신 결과에 따라 콜백 메소드를 실행할 콜백 객체 생성
        // 매개값은 MqttCallback 객체
        mqttClient.setCallback(mqttCallBack);
        // MQTT 브로커와 연결하기
        mqttClient.connect();
    }

    // Method
    public void publish(String text) throws MqttException {
        // MQTT 브로커로 보내는 메시지 생성
        MqttMessage mqttMessage = new MqttMessage(text.getBytes());
        // 지정한 MQTT 브로커로 메시지 보냄
        // topic, 보내고자 하는 메시지
        mqttClient.publish("/devices/device1/temperature", mqttMessage);
    }

    public void shutdown() throws MqttException {
        // MQTT 브로커와 연결 끊기
        mqttClient.disconnect();
        // MQTT 브로커가 사용한 리소스(메모리) 해제
        mqttClient.close();
    }

    public static void main(String[] args) throws Exception {
        MqttPublisher publisher = new MqttPublisher();
        // 매 1초 단위로 온도 메시지 보냄
        for (int i = 1; i <= 5; i++) {
            publisher.publish("temperature: " + i);
            Thread.sleep(1000);
        }
        publisher.shutdown();
    }
}