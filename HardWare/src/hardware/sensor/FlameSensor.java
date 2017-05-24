package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.converter.PCF8591;

public class FlameSensor {

    // Field
    private PCF8591 pcf8591;
    private GpioPinDigitalInput gpioPinDigitalInput;

    // Construrctor
    public FlameSensor(PCF8591 pcf8591, Pin digitalPinNo) {
        this.pcf8591 = pcf8591;
        GpioController gpioController = GpioFactory.getInstance();
        gpioPinDigitalInput = gpioController.provisionDigitalInputPin(digitalPinNo);
        // true 입출력 모드 해제 , 상태를 LOW로
        gpioPinDigitalInput.setShutdownOptions(true, PinState.LOW);
    }

    // Method
    public double getValue() throws Exception {
        int analogVal = pcf8591.analogRead(); // 0~255        
        return analogVal;
    }

    public void setGpioPinListenerDigital(GpioPinListenerDigital listener) {
        gpioPinDigitalInput.addListener(listener);
    }

    public static void main(String[] args) throws Exception {
        PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0);
        FlameSensor test = new FlameSensor(pcf8591, RaspiPin.GPIO_00);

        // How1: Digital  값을 이용해서 처리 (DO 의 상태 감지)
        test.setGpioPinListenerDigital(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.LOW) {
                    System.out.println("###################: 화제 발생");
                } else {
                    System.out.println("###################: 정상 상태");
                }
            }
        });

        // How2: Analog  값을 이용해서 처리
        while (true) {
            double value = test.getValue();
            System.out.println(value);
            if (value < 100) {

            }
            Thread.sleep(1000);
        }   
    }
}
