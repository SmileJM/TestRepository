package coap.exam04.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

public class CoapResource04 extends CoapResource {
    // Field
    private int value;

    // Constructor
    public CoapResource04() {
        super("resource04");
        // 관찰 기능 활성화
        setObservable(true);
        // 관찰기능을 제공하는 것을 클라이언트가 알 수 있도록 설정
        getAttributes().setObservable();
    }

    // Method
    @Override
    public void handleGET(CoapExchange exchange) {
        // 관찰 요청을 한 클라이언트에게 응답을 보냄
        exchange.respond("value: " + value);
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        JSONObject jsonObject = new JSONObject(exchange.getRequestText());
        value = jsonObject.getInt("value");
        if (value > 30) {
            changed();
        }
        // 항상 exchange.respond() 을 보내줘야 함
        exchange.respond("ok");
    }
}
