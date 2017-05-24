package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.sensor.ThermistorSensor;

public class ThermistorBuzzerTest {

    public static void main(String[] args) throws Exception {
        PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0);
        ThermistorSensor test = new ThermistorSensor(pcf8591);
        ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_00);
        while (true) {
            double value = test.getValue();
            if (value >= 28) {
                if (activeBuzzer.getStatus().equals("off")) {
                    activeBuzzer.on();
                }
            } else {
                if (activeBuzzer.getStatus().equals("on")) {
                    activeBuzzer.off();
                }
            }
            System.out.println(value);
            Thread.sleep(1000);
        }
    }
}
