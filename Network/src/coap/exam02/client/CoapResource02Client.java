package coap.exam02.client;

import java.awt.BorderLayout;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class CoapResource02Client {

    // Field
    private CoapClient coapClient;

    // Constructor
    public CoapResource02Client() {
        this.coapClient = new CoapClient();
    }

    // Method
    public String get(int angle) {
        String queryString = "kind=ultrasonicsensor&angle=" + angle;
        // 프토토콜 스키마는 coap        
        coapClient.setURI("coap://192.168.3.50/resource02?" + queryString);
        CoapResponse response = coapClient.get();
        if (response == null) {
            return get(angle);
        } else if (response.getCode() == CoAP.ResponseCode.CONTENT) {
            return response.getResponseText();
        } else {
            return get(angle);
        }
    }

    public String post(int angle) {
        JSONObject jsonObject = new JSONObject(); // {} 를 만들기 위해서(객체) / [] 배열을 만들려면 JSONArray 사용
        jsonObject.put("kind", "ultrasonicsensor");
        jsonObject.put("angle", angle);
        String json = jsonObject.toString(); // 완성된 jsonObject 얻기
        
        coapClient.setURI("coap://192.168.3.50/resource02");
        CoapResponse response = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
        if (response == null) {
            return get(angle);
        } else if (response.getCode() == CoAP.ResponseCode.CONTENT) {
            return response.getResponseText();
        } else {
            return get(angle);
        }    
    }

    public void shutdown() {
        coapClient.shutdown();
    }

    public static void main(String[] args) {
        CoapResource02Client client = new CoapResource02Client();
        String text = "";
        for (int i = 10; i <= 170; i += 10) {
//            text = client.get(kind, i);
            text = client.post(i);
            System.out.println("angle: " + i + " / " + text);
        }
        client.shutdown();
        text = client.post(90);
        client.shutdown();
        System.out.println("angle: " + 90 + " / " + text);
    }
}
