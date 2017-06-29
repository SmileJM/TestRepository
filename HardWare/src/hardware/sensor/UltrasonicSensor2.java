package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UltrasonicSensor2 {

    // Filed
    private GpioPinDigitalOutput trigPin;
    private GpioPinDigitalInput echoPin;

    private int previousDistance;
    private boolean again;
    private int count;

    // Constructor
    public UltrasonicSensor2(Pin trigPinNo, Pin echoPinNo) {
        GpioController gpioController = GpioFactory.getInstance();
        trigPin = gpioController.provisionDigitalOutputPin(trigPinNo, PinState.LOW);
        trigPin.setShutdownOptions(true, PinState.LOW);
        echoPin = gpioController.provisionDigitalInputPin(echoPinNo);
        echoPin.setShutdownOptions(true, PinState.LOW);
    }

    // Method
    public int getDistance() {
        // 초음파 발신시간 변수, 수신시간 변수
        double start = 0;
        double end = 0;

        // 초기화
        trigPin.low();
        try {
            Thread.sleep(0, 5000);
        } catch (InterruptedException ex) {
        }

        // 초음파를 10 마이크로 초 동안 발생        
        trigPin.high();
        try {
            Thread.sleep(0, 10000);
        } catch (InterruptedException ex) {

        }
        trigPin.low();
        count = 0;
        // echoPin 이 HIGH가 될 때까지 기다림
        while (echoPin.isLow()) {
            count++;
            if (count > 50000) {
                return getDistance();
            }
        }
        start = System.nanoTime();

        while (echoPin.isHigh()) {
            count++;
            if (count > 50000) {
                return getDistance();
            }
        }

        end = System.nanoTime();
        // 편도 시간 (sec)
        double seconds = (end - start) / 1000000000 / 2;

        // 거리(cm)
        int distance = (int) (seconds * (33130 + 60.6 * 25));

        // 100 이상 튀는 값이 있을 경우 다시 측정
        if (again == false && Math.abs(previousDistance - distance) > 100) {
            again = true;
            getDistance();
            getDistance(); // dummy read
            distance = getDistance();
        } else {
            again = false;
        }

        // 초과값 검사(2cm ~ 400cm)
        if (distance < 2) {
            distance = 2;
        } else if (distance > 400) {
            distance = 400;
        }
        previousDistance = distance;

        return distance;
    }

    public static void main(String[] args) throws InterruptedException {
        UltrasonicSensor2 ultrasonicSensor = new UltrasonicSensor2(RaspiPin.GPIO_28, RaspiPin.GPIO_29);

        while (true) {
            int distance = ultrasonicSensor.getDistance();
            System.out.println("거리: " + distance + " cm");
            Thread.sleep(1000);
        }

    }

}