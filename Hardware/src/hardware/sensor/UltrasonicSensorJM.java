package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class UltrasonicSensorJM {
    //Field

    private GpioPinDigitalOutput trigPin;
    private GpioPinDigitalInput echoPin;

    private int previousDistance;
    private boolean again;
    private int count;

    //Constructor
    public UltrasonicSensorJM(Pin trigPinNo, Pin echoPinNo) {
        GpioController gpioController = GpioFactory.getInstance();

        trigPin = gpioController.provisionDigitalOutputPin(trigPinNo, PinState.LOW);
        trigPin.setShutdownOptions(true, PinState.LOW);

        echoPin = gpioController.provisionDigitalInputPin(echoPinNo);
        echoPin.setShutdownOptions(true, PinState.LOW);
    }

    public int getDistance() {
        //초음파 발신 시간 변수와 수신 시간 변수 선언
        double start = 0;
        double end = 0;

        //초음파를 10 마이크로초 동안 발생
        trigPin.high();
        try {
            Thread.sleep(0, 10000);
        } catch (Exception e) {
        }
        trigPin.low();
        //echoPin이 High가 될때까지 기다림
        count = 0;
        while (echoPin.isLow()) {
            try {
                Thread.sleep(0, 1);
            } catch (Exception e) {
            }
        }
        //발신 시간을 저장
        start = System.nanoTime();
        double currTime = 0;
        //echoPin이 Low가 될때까지 기다림
        count = 0;
        while (echoPin.isHigh()) {
            currTime = System.nanoTime();
            System.out.println(currTime - start);
            if ((currTime - start) > 60000000) {
                return getDistance();
            }
        }
        end = System.nanoTime();
        //편도 시간(sec)
        double seconds = (end - start) / 1000;
        //거리(cm)
        int distance = (int) (seconds / 58.82352);

        //초과값 검사(2cm ~ 400cm)
        if (distance < 2) {
            distance = 2;
        } else if (distance > 400) {
            distance = 400;
        }

        return distance;
    }

    //Method
    public static void main(String[] args) throws Exception {
        UltrasonicSensorJM test = new UltrasonicSensorJM(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
        System.out.println("~~~");

        while (true) {
            int distance = 0;
            for (int i = 0; i < 10; i++) {
                distance += test.getDistance();
                Thread.sleep(100);
            }
            distance = distance / 10;
            System.out.println("거리(cm): " + distance);
        }
    }
}
