package coap.exam04.client;

import java.io.IOException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;

public class CoapResource04Client {

    // Field
    private CoapClient coapClient;
    private CoapObserveRelation coapObserveRelation;

    // Constructor
    public CoapResource04Client() {
        coapClient = new CoapClient();
    }

    // Method
    // observe() 관찰을 요청하는 메소드
    public void observe() {
        coapClient.setURI("coap://192.168.3.50/resource04");
        coapObserveRelation = coapClient.observe(new CoapHandler() {
            // 관찰을 비동기로 받음 CoapHandler()
            @Override
            public void onLoad(CoapResponse resource) {
                String text = resource.getResponseText();
                System.out.println(text);
            }

            @Override
            public void onError() {
            }
        });
    }

    public void shutdown() {
        // 클라이언트가 종료될 때 관찰 기능을 중지함
        coapObserveRelation.proactiveCancel();
        // 클라이언트를 안전하게 종료
        coapClient.shutdown();
    }

    public static void main(String[] args) throws IOException {
        CoapResource04Client client = new CoapResource04Client();
        client.observe();
        System.in.read();
        client.shutdown();
    }
}
