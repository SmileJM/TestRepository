package coap.exam03.client;

import java.io.IOException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class CoapResource03AsyncClient {

    // Field
    private CoapClient coapClient;

    // Constructor
    public CoapResource03AsyncClient() {
        coapClient = new CoapClient();
//        coapClient.useCONs();
//        coapClient.useNONs();
    }

    // Method
    public void get(int angle) {
        String queryString = "kind=ultrasonicsensor&angle=" + angle;
        coapClient.setURI("coap://192.168.3.50/resource02?" + queryString);

        coapClient.get(new CoapHandler() {
            @Override
            public void onLoad(CoapResponse response) {
                // 응답이 왔을 때 실행됨
                if (response.getCode() == CoAP.ResponseCode.CONTENT) {
                    String text = response.getResponseText();
                    System.out.println("angle: " + angle + "도 / " + text + "cm");
                }
            }

            @Override
            public void onError() {
                // 에러 발생시 실행됨
            }
        });
    }

    public void post(int angle) {
        JSONObject jsonObject = new JSONObject(); // {} 를 만들기 위해서(객체) / [] 배열을 만들려면 JSONArray 사용
        jsonObject.put("kind", "ultrasonicsensor");
        jsonObject.put("angle", angle);
        String json = jsonObject.toString(); // 완성된 jsonObject 얻기

        coapClient.setURI("coap://192.168.3.50/resource02");

        coapClient.post(new CoapHandler() {
            @Override
            public void onLoad(CoapResponse response) {
                if (response.getCode() == CoAP.ResponseCode.CONTENT) {
                    String text = response.getResponseText();
                    System.out.println("angle: " + angle + "도 / " + text );
                }
            }

            @Override
            public void onError() {

            }
        }, json, MediaTypeRegistry.APPLICATION_JSON);
    }

    public void shutdown() {
        coapClient.shutdown();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CoapResource03AsyncClient client = new CoapResource03AsyncClient();
        for (int i = 10; i <= 170; i += 10) {
//            client.get(i);
            client.post(i);
            Thread.sleep(500);
        }
        System.in.read();
        client.shutdown();
        client.post(90);
        client.shutdown();
    }
}
