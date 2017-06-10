package coap.exam01.server;

import hardware.converter.PCF8591;
import hardware.sensor.ThermistorSensor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapResource01 extends CoapResource {
    // Field
    private ThermistorSensor thermistorSensor;
    
    // Constructor
    public CoapResource01() {
        super("resource01"); // 리소스 식별이름
        PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
        thermistorSensor = new ThermistorSensor(pcf8591);
    }
    // Method

    @Override
    public void handleGET(CoapExchange exchange) {
        try {
            double value = thermistorSensor.getValue();
            exchange.respond("온도: " + value + "도");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
