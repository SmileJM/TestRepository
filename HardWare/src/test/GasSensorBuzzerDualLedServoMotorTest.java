package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.led.DualColorLed;
import hardware.motor.SG90Servo;
import hardware.sensor.GasSensor;

public class GasSensorBuzzerDualLedServoMotorTest {

    public static void main(String[] args) throws Exception {
        ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_27);
        DualColorLed led = new DualColorLed(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
        SG90Servo motor = new SG90Servo(RaspiPin.GPIO_01, 8, 24);
        PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0);
        GasSensor gas = new GasSensor(pcf8591, RaspiPin.GPIO_00);

        gas.setGpioPinListenerDigital(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.LOW) {
                    System.out.println("********************** 가스 감지");
                } else {
                    System.out.println("********************** 정상 상태");
                }
            }
        }
        );

        while (true) {
            double value = gas.getValue();
            if (value > 230) {
                led.red();
                motor.setAngle(180);
                activeBuzzer.on();
            } else {
                led.green();
                motor.setAngle(0);
                activeBuzzer.off();
            }
            System.out.println(value);
            Thread.sleep(1000);
        }
    }
}
