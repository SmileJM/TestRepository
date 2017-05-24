package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.converter.PCF8591;
import hardware.led.RgbLedDigital;
import hardware.sensor.PhotoresistorSensor;

public class PhotoresistorSensorRGBTest {

    public static void main(String[] args) throws Exception {
        PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0);
        PhotoresistorSensor photoresistor = new PhotoresistorSensor(pcf8591);
        RgbLedDigital rgb = new RgbLedDigital(RaspiPin.GPIO_27, RaspiPin.GPIO_28, RaspiPin.GPIO_29);

        while (true) {
            double value = photoresistor.getValue();
            System.out.println(value);
//            if (value > 200) {
//                rgb.rgb(false, false, false);
//            } else if (value > 100) {
//                rgb.rgb(true, false, false);
//            } else if (value > 50) {
//                rgb.rgb(false, true, false);
//            } else if( value >20) {
//                 rgb.rgb(false, false, true);
//            } else {
//                rgb.rgb(true, true, true);
//            }
            if( value <10 ) {
                 rgb.rgb(false, false, false);
            } else {
                 rgb.rgb(true, true, true);
            }
            Thread.sleep(500);
        }
    }
}
