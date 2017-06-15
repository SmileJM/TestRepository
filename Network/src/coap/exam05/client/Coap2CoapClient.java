package coap.exam05.client;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;

public class Coap2CoapClient {

    // Field
    private CoapClient coapClient;

    // Constructor
    public Coap2CoapClient() {
        coapClient = new CoapClient();
    }

    // Method
    public String coap2coap() {
        // Proxy의 접근 URI
        coapClient.setURI("coap://192.168.3.50:5683/coap2coap");
        // Forward 되는 리소스의 통신 방식
        Request request = new Request(CoAP.Code.GET);
        // Forward 되는 리소스의 URI
//        request.getOptions().setProxyUri("coap://192.168.3.50/resource01");
        request.getOptions().setProxyUri("coap://localhost/resource01");
      
        // 통신
        CoapResponse response = coapClient.advanced(request);

        if (response.getCode() == CoAP.ResponseCode.CONTENT) {
            return response.getResponseText();
        } else {
            return null;
        }
    }

    public void shutdown() {
        coapClient.shutdown();
    }

    public static void main(String[] args) {
        Coap2CoapClient client = new Coap2CoapClient();
        String text = client.coap2coap();
        System.out.println(text);
        client.shutdown();
    }
}
