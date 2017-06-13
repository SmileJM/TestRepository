package coap.exam02.server;

import com.pi4j.io.gpio.RaspiPin;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.UltrasonicSensor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

public class CoapResource02 extends CoapResource {

    // Field
    private PCA9685 pca9685;
    private SG90ServoPCA9685Duration servoMotor;
    int distance;
    UltrasonicSensor ultrasonicSensor;
// Constructor

    public CoapResource02() {
        super("resource02");
        try {
            pca9685 = PCA9685.getInstance();
            servoMotor = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);
            ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
            Thread thread = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        distance = ultrasonicSensor.getDistance();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            };
            thread.start();
        } catch (Exception ex) {
            Logger.getLogger(CoapResource02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method
    @Override
    public void handleGET(CoapExchange exchange) {
        String kind = exchange.getRequestOptions().getUriQuery().get(0).split("=")[1];
        int angle = Integer.parseInt(exchange.getRequestOptions().getUriQuery().get(1).split("=")[1]);

        if (kind.equals("ultrasonicsensor")) {
            servoMotor.setAngle(angle);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
            exchange.respond("distance: " + distance + "cm");
        } else {
            exchange.respond("ok");
        }
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        String json = exchange.getRequestText();
        System.out.println(json);

        JSONObject jsonObject = new JSONObject(json);
        String kind = jsonObject.getString("kind");
        int angle = jsonObject.getInt("angle");

        if (kind.equals("ultrasonicsensor")) {
            servoMotor.setAngle(angle);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
            exchange.respond("distance: " + distance + "cm");
        } else {
            exchange.respond("ok");
        }
    }

}
