package coap.exam04.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapResource03 extends CoapResource {

    // Field
    private int value;

    // Constructor
    public CoapResource03() {
        super("resource03");
        // 관찰 기능 활성화
        setObservable(true);
        // 관찰기능을 제공하는 것을 클라이언트가 알 수 있도록 설정
        getAttributes().setObservable();
        // Test
        Thread thread = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    value = i;
                    changed();
                    i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        thread.start();
    }

    // Method
    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond("value: " + value);
    }

    @Override
    public void handlePOST(CoapExchange exchange) {

    }

}
