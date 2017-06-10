package coap.exam01.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;

public class CoapResource01Client {

    // Field
    private CoapClient coapClient;

    // Constructor
    public CoapResource01Client() {
        this.coapClient = new CoapClient();
    }

    // Method
    public String get() {
        // 프토토콜 스키마는 coap        
        coapClient.setURI("coap://192.168.3.50/resource01");
        CoapResponse response = coapClient.get();
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
        CoapResource01Client client = new CoapResource01Client();
        while(true) {
        String text = client.get();
        System.out.println(text);
        client.shutdown();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CoapResource01Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
