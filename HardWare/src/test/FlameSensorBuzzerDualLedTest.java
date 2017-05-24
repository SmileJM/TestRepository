package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.led.DualColorLed;
import hardware.sensor.FlameSensor;

public class FlameSensorBuzzerDualLedTest {

    // 온도 높으면 부저 + R 
    public static void main(String[] args) throws Exception {
        DualColorLed led = new DualColorLed(RaspiPin.GPIO_01, RaspiPin.GPIO_03);
        ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_00);
        PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0);
        FlameSensor flame = new FlameSensor(pcf8591, RaspiPin.GPIO_02);

        while (true) {
            double value = flame.getValue();
            System.out.println(value);
            if (value < 50) {
                activeBuzzer.on();
                led.red();
            } else {
                activeBuzzer.off();
                led.green();
            }
            Thread.sleep(1000);
        }
    }
}
