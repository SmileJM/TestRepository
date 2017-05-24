package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class DualColorLed {

    // Field
    // 디지털 출력 핀 객체 필드
    private GpioPinDigitalOutput redPin;
    private GpioPinDigitalOutput greenPin;

    // Constructor
    public DualColorLed(Pin redPinNo, Pin greenPinNo) {
        
        // GPIO핀을 고정적으로 쓰지 않고 외부로부터 핀을 받기 위해서
        // GPIOController 객체 얻기
        GpioController gpioController = GpioFactory.getInstance();
        // 디지털 출력 핀 생성
        redPin = gpioController.provisionDigitalOutputPin(redPinNo);
        // JVM(애플리케이션)이 종료되었을 때 주는 옵션(출력 모드 해제, 핀의 출력을 LOW(0))
        redPin.setShutdownOptions(true, PinState.LOW);
        greenPin = gpioController.provisionDigitalOutputPin(greenPinNo);
        greenPin.setShutdownOptions(true, PinState.LOW);
    }

    // Method
    public void red() {
        redPin.high();
        greenPin.low();
    }

    public void green() {
        redPin.low();
        greenPin.high();
    }

    public static void main(String[] args) throws InterruptedException {
        DualColorLed test = new DualColorLed(RaspiPin.GPIO_00, RaspiPin.GPIO_01);
//        test.green();
        while (true) {
            test.green();
            Thread.sleep(3000);
            test.red();
            Thread.sleep(1000);
        }
    }
}
