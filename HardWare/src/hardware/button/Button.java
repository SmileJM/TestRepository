package hardware.button;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.io.IOException;

public class Button {

    // Field
    private GpioPinDigitalInput gpioPinDigitalInput;

    // Constructor
    public Button(Pin buttonPinNo) {
        GpioController gpioControll = GpioFactory.getInstance();
        gpioPinDigitalInput = gpioControll.provisionDigitalInputPin(buttonPinNo);
        // 입력 모드 해제
        gpioPinDigitalInput.setShutdownOptions(true);
    }

    // Method
    public void setGpioPinListenerDigital(GpioPinListenerDigital listener) {
        gpioPinDigitalInput.addListener(listener);
    }

    public static void main(String[] args) throws IOException {
        Button button = new Button(RaspiPin.GPIO_00);
        button.setGpioPinListenerDigital(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.HIGH) {
                    System.out.println("High");
                } else {
                    System.out.println("Low");
                }
            }
        });
        System.out.println("Ready...");
        System.in.read();
    }
}
