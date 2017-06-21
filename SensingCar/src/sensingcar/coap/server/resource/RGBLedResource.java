package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.led.RgbLedPWM;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sensingcar.SensingCar;

public class RGBLedResource extends CoapResource {

    // Field
    private static final Logger logger = LoggerFactory.getLogger(SensingCar.class);
    private RgbLedPWM rgbLed;
    private int currRed;
    private int currGreen;
    private int currBlue;

    // Constructor
    public RGBLedResource() throws Exception {
        super("lgbled");
        rgbLed = new RgbLedPWM(RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06);
        setColor(0, 0, 0);
    }

    // Method
    private void setColor(int red, int green, int blue) {
        rgbLed.ledColorSet(red, green, blue);
        currRed = red;
        currGreen = green;
        currBlue = blue;
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        // {"command":"change", "red":"90", "green":"10", "blue":"50"}
        // {"command":"status"}
        try {
            String requestJson = exchange.getRequestText();
            JSONObject requestJsonObject = new JSONObject(requestJson);
            String command = requestJsonObject.getString("command");
            if (command.equals("change")) {
                int red = Integer.parseInt(requestJsonObject.getString("red"));
                int green = Integer.parseInt(requestJsonObject.getString("green"));
                int blue = Integer.parseInt(requestJsonObject.getString("blue"));
                setColor(red, green, blue);
            } else if (command.equals("status")) {

            }
            JSONObject responseJsonObject = new JSONObject();
            responseJsonObject.put("result", "success");
            responseJsonObject.put("red", currRed);
            responseJsonObject.put("green", currGreen);
            responseJsonObject.put("blue", currBlue);
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
