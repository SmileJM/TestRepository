package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.sensor.UltrasonicSensor;
import java.io.IOException;

public class UltrasonicSensorBuzzerTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_03);
        UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_00, RaspiPin.GPIO_01);

        while (true) {
            if (ultrasonicSensor.getDistance() < 20) {
                activeBuzzer.on();
            } else {
                activeBuzzer.off();
            }
//            System.out.println(ultrasonicSensor.getDistance());
//            Thread.sleep(1000);
        }
//        System.in.read();
    }
}
