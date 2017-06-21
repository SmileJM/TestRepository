package sensingcar.coap.server.resource;

import hardware.lcd.LCD1602;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LcdResource extends CoapResource {

    // Field
    private static final Logger logger = LoggerFactory.getLogger(LcdResource.class);
    private LCD1602 lcd;
    private String currLing0;
    private String currLing1;

    // Constructor
    public LcdResource() throws Exception {
        super("lcd");
        lcd = new LCD1602(0x27);
        setText("RPI-15-2", "192.168.3.50");
    }

    // Method
    private void setText(String line0, String line1) {
        lcd.clear();
        lcd.write(0, 0, line0);
        lcd.write(1, 0, line1);
        currLing0 = line0;
        currLing1 = line1;
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        // {"command":"change", "line0":"xxx", "line0":"xxx"}
        // {"command":"status"}
        try {
            String requestJson = exchange.getRequestText();
            JSONObject requestJsonObject = new JSONObject(requestJson);
            String command = requestJsonObject.getString("command");
            if (command.equals("change")) {
                String line0 = requestJsonObject.getString("line0");
                String line1 = requestJsonObject.getString("line1");
                setText(line0, line1);
            } else if (command.equals("status")) {

            }
            JSONObject responseJsonObject = new JSONObject();
            responseJsonObject.put("result", "success");
            responseJsonObject.put("line0", currLing0);
            responseJsonObject.put("line1", currLing1);
            String responseJson = responseJsonObject.toString();
            exchange.respond(responseJson);
        } catch (Exception e) {
            JSONObject responseJsonObject = new JSONObject();
            responseJsonObject.put("result", "fail");
            String responseJson = responseJsonObject.toString();
            exchange.respond(responseJson);
        }
    }
}
