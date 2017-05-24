package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.button.Button;
import hardware.led.RgbLedDigital;
import java.io.IOException;

public class ButtonTest2 {

    public static void main(String[] args) throws IOException {
        Button buttonR = new Button(RaspiPin.GPIO_00);
        Button buttonG = new Button(RaspiPin.GPIO_01);
        Button buttonB = new Button(RaspiPin.GPIO_02);

        RgbLedDigital rgb = new RgbLedDigital(RaspiPin.GPIO_27, RaspiPin.GPIO_28, RaspiPin.GPIO_29);

        buttonR.setGpioPinListenerDigital(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.LOW) {
                    rgb.red(true);
                } else {
                    rgb.red(false);
                }
            }
        });
        buttonG.setGpioPinListenerDigital(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.LOW) {
                    rgb.green(true);
                } else {
                    rgb.green(false);
                }
            }
        });
        buttonB.setGpioPinListenerDigital(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.LOW) {
                    rgb.blue(true);
                } else {
                    rgb.blue(false);
                }
            }
        });
        System.out.println("Ready...");

        System.in.read();
    }
}
