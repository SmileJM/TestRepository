package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import java.io.IOException;

public class RgbLedPWM {

    // Field
    private GpioPinPwmOutput redPin;
    private GpioPinPwmOutput greenPin;
    private GpioPinPwmOutput bluePin;

    private int[] currentColorSet = new int[3];

    // Constructor
    public RgbLedPWM(Pin redPinNo, Pin greenPinNo, Pin bluePinNo) {
        GpioController gpioController = GpioFactory.getInstance();
        // 소프트웨어 PWM 출력 핀 객체 생성
        redPin = gpioController.provisionSoftPwmOutputPin(redPinNo);
        greenPin = gpioController.provisionSoftPwmOutputPin(greenPinNo);
        bluePin = gpioController.provisionSoftPwmOutputPin(bluePinNo);
        // 제어 단계를 255 단계
        redPin.setPwmRange(255);
        greenPin.setPwmRange(255);
        bluePin.setPwmRange(255);
        // 처음에 발광하지 않도록
        redPin.setPwm(255);
        greenPin.setPwm(255);
        bluePin.setPwm(255);

        ledColorSet(0, 0, 0);
    }

    // Method
    public void ledColorSet(int r, int g, int b) {
        currentColorSet[0] = r;
        currentColorSet[1] = g;
        currentColorSet[2] = b;
        
        r = 255 - r;
        g = 255 - g;
        b = 255 - b;
        
        redPin.setPwm(r);
        greenPin.setPwm(g);
        bluePin.setPwm(b);
    }

    public int[] getCurrentColorSet() {
        return currentColorSet;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        RgbLedPWM test = new RgbLedPWM(RaspiPin.GPIO_00, RaspiPin.GPIO_02, RaspiPin.GPIO_03);

        test.ledColorSet(255, 0, 0);        Thread.sleep(500);
        test.ledColorSet(0, 255, 0);        Thread.sleep(500);
        test.ledColorSet(0, 0, 255);        Thread.sleep(500);
        test.ledColorSet(255, 255, 0);     Thread.sleep(500);
        test.ledColorSet(255, 0, 255);     Thread.sleep(500);
        test.ledColorSet(0, 255, 255);     Thread.sleep(500);
        test.ledColorSet(255, 255, 255);  Thread.sleep(500);
        test.ledColorSet(0, 0, 0);           Thread.sleep(500);
    }
}
