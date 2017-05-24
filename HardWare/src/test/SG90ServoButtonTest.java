package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.button.Button;
import hardware.motor.SG90Servo;
import java.io.IOException;

public class SG90ServoButtonTest {
// 버튼 4개 눌러서
//    그에 맞는 각도로 이동시키기

    public static void main(String[] args) throws IOException {
        Button button1 = new Button(RaspiPin.GPIO_00);
        Button button2 = new Button(RaspiPin.GPIO_02);
        Button button3 = new Button(RaspiPin.GPIO_03);
        Button button4 = new Button(RaspiPin.GPIO_04);

        SG90Servo servo = new SG90Servo(RaspiPin.GPIO_01, 8, 24);

        button1.setGpioPinListenerDigital(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.LOW) {
                    System.out.println("60 도");
                    servo.setAngle(60);
                }
            }
        });
        button2.setGpioPinListenerDigital(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.LOW) {
                    System.out.println("120 도");
                    servo.setAngle(120);
                }
            }
        });
        button3.setGpioPinListenerDigital(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.LOW) {
                    System.out.println("180 도");
                    servo.setAngle(180);
                }
            }
        });
        button4.setGpioPinListenerDigital(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.LOW) {
                    System.out.println("0 도");
                    servo.setAngle(0);
                }
            }
        });
        System.out.println("Ready...");

        System.in.read();
    }

}
