package coap.exam04.server;

import com.pi4j.io.gpio.RaspiPin;
import hardware.converter.PCF8591;
import hardware.led.RgbLedPWM;
import hardware.sensor.GasSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapResource06 extends CoapResource {

    // Field
    private double value;
    private GasSensor gasSensor;
    private boolean state;
    private RgbLedPWM rgb;

    // Constructor
    public CoapResource06() {
        super("resource06");
        PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN2);
        gasSensor = new GasSensor(pcf8591, RaspiPin.GPIO_23);
        rgb = new RgbLedPWM(RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    value = gasSensor.getValue();
                    if (value > 200) {
                        state = true;
                    } else {
                        state = false;
                    }
                } catch (Exception ex) {
                }
                while (true) {
                    try {
                        value = gasSensor.getValue();
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
        if (value > 200 && state) {
            exchange.respond("value: " + value + " 가스 검출!!");
            rgb.ledColorSet(255, 0, 0);
            state = !state;
        } else if (value <= 200 && !state) {
            exchange.respond("value: " + value + " 정상 상태!!");
            rgb.ledColorSet(0, 255, 0);
            state = !state;
        }
    }
}
