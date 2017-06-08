package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.io.IOException;

public class TrackingSensor {

    // Field
    private GpioPinDigitalInput trackingPin;

    // Constructor
    public TrackingSensor(Pin trackingPinNo) {
        GpioController gpioController = GpioFactory.getInstance();
        trackingPin = gpioController.provisionDigitalInputPin(trackingPinNo);
        trackingPin.setShutdownOptions(true, PinState.LOW);
    }

    // Method
    public void setGpioPinDigital(GpioPinListenerDigital gpioPinListenerDigital) {
        trackingPin.addListener(gpioPinListenerDigital);
    }

    public PinState getStatus() {
        return trackingPin.getState();
    }

    public static void main(String[] args) throws IOException {
        TrackingSensor trackingSensor = new TrackingSensor(RaspiPin.GPIO_26);
        trackingSensor.setGpioPinDigital(event -> {
            if( event.getState() == PinState.HIGH) {
                System.out.println("Black");
            } else {
                System.out.println("White");
            }
        });
        System.out.println("Ready");
        System.in.read();
    }
}
