package mqtt.exam02;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

public class Distributor {

    // Field
    private String url = "tcp://localhost:1883";
    private String clientId = "distributor";
    private String deviceRequest = "/devices/+/request";
    // %s : 매개변수 처리
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

    // Constructor
    public Distributor() throws MqttException {
        mqttClient = new MqttClient(url, clientId);
        mqttClient.setCallback(callback);
        mqttClient.connect();
    }

    // Method
    public void subscribe() throws MqttException {
        mqttClient.subscribe(deviceRequest);
    }

    public void publish(String json) throws MqttException {
        JSONObject jsonObject = new JSONObject(json);
        String to = jsonObject.getString("to");
        String targetResponse = String.format(deviceResponse, to);
        MqttMessage mqttMessage = new MqttMessage(json.getBytes());
        mqttMessage.setQos(qos);
        mqttClient.publish(targetResponse, mqttMessage);
    }

    public void close() throws MqttException {
        if (mqttClient != null) {
            mqttClient.disconnect();
            mqttClient.close();
            mqttClient = null;
        }
    }
}
