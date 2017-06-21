package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.UltrasonicSensor;
import java.util.logging.Level;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UltrasonicSensorResource extends CoapResource {

    // Field
    private static final Logger logger = LoggerFactory.getLogger(UltrasonicSensorResource.class);
    private PCA9685 pca9685;
    private SG90ServoPCA9685Duration servoMotor;
    private UltrasonicSensor ultrasonicSensor;
    private final int minAngle = 10;
    private final int maxAngle = 170;
    private int currAngle;
    private int currDistance;

    // Constructor
    public UltrasonicSensorResource() throws Exception {
        super("ultrasonicsensor");
        setObservable(true);
        getAttributes().setObservable();
        setObserveType(CoAP.Type.NON);

        pca9685 = PCA9685.getInstance();
        servoMotor = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);
        ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
        Thread thread = new Thread() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    try {
                        currDistance = ultrasonicSensor.getDistance();
                        if (count == 2) {
                            changed();
                            count = 0;
                        }
                        Thread.sleep(500);
                        count++;
                    } catch (Exception e) {
                        logger.info(e.toString());
                    }
                }
            }
        };
        thread.start();
        setAngle(90);
    }

    // Method
    public void setAngle(int angle) {
        if (angle <= minAngle) {
            angle = minAngle;
        } else if (angle >= maxAngle) {
            angle = maxAngle;
        }
        currAngle = angle;
        servoMotor.setAngle(angle);
    }

    // 관찰 요청을 한 클라이언트를 위해서
    @Override
    public void handleGET(CoapExchange exchange) {
        JSONObject responseJsonObject = new JSONObject();
        responseJsonObject.put("angle", String.valueOf(currAngle));
        responseJsonObject.put("distance", String.valueOf(currDistance));
        String responseJson = responseJsonObject.toString();
        exchange.respond(responseJson);
    }

    // 관찰 요청을 하지 않는 클라이언트를 위해서
    @Override
    public void handlePOST(CoapExchange exchange) {
        // {"command":"change", "angle":"90"}
        // {"command":"status"}
        try {
            String requestJson = exchange.getRequestText();
            JSONObject requestJsonObject = new JSONObject(requestJson);
            String command = requestJsonObject.getString("command");
            if (command.equals("change")) {
                int angle = Integer.parseInt(requestJsonObject.getString("angle"));
                setAngle(angle);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            } else if (command.equals("status")) {

            }
            JSONObject responseJsonObject = new JSONObject();
            responseJsonObject.put("result", "success");
            responseJsonObject.put("angle", currAngle);
            responseJsonObject.put("distance", currDistance);
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
