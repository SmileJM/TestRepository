package coap.exam04.server;

import hardware.converter.PCF8591;
import hardware.sensor.ThermistorSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapResource05 extends CoapResource {

    // Field
    private double value;
    private ThermistorSensor thermistorSensor;
    private  PCF8591 pcf8591;

    // Constructor
    public CoapResource05() {
        super("resource05");
        pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
        thermistorSensor = new ThermistorSensor(pcf8591);
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        value = thermistorSensor.getValue();                        
                        changed();
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                    }
                }
            }
        };
        thread.start();
        // 관찰 기능 활성화
        setObservable(true);
        // 관찰기능을 제공하는 것을 클라이언트가 알 수 있도록 설정
        getAttributes().setObservable();
    }

    // Method    
    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond("value: " + value);
    }
}
