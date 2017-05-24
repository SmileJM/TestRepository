package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.sensor.TrackingSensor;
import java.io.IOException;

public class TrackingSensorBuzzerTest {

    public static void main(String[] args) throws IOException {
        TrackingSensor trackingSensor = new TrackingSensor(RaspiPin.GPIO_00);
        ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_02);

        trackingSensor.setGpioPinDigital(event -> {
            if (event.getState() == PinState.LOW) {
                activeBuzzer.on();
            } else {
                activeBuzzer.off();
            }
        });
        System.out.println("Ready...");
        System.in.read();
    }
}
