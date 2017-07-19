package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class UltrasonicSensor {

    //Field
    private static final int SOUND_VALOCITY = 34000; // (cm/s)
    private static final int NANO_SECOND = 1000000000;

    private GpioPinDigitalOutput trigPin;
    private GpioPinDigitalInput echoPin;

    private int previousDistance;
    private boolean again;

    //Constructor
    public UltrasonicSensor(Pin trigPinNo, Pin echoPinNo) {
        GpioController gpioController = GpioFactory.getInstance();

        trigPin = gpioController.provisionDigitalOutputPin(trigPinNo, PinState.LOW);
        trigPin.setShutdownOptions(true, PinState.LOW);

        echoPin = gpioController.provisionDigitalInputPin(echoPinNo, PinPullResistance.PULL_DOWN);
        echoPin.setShutdownOptions(true, PinState.LOW);
    }

    public int getDistance() {
        //초음파 발신 시간 변수와 수신 시간 변수 선언
        double startTime = 0;
        double endTime = 0;

        //초음파를 10 마이크로초 동안 발생
        trigPin.high();
        try {
            Thread.sleep((long) 0.01);
        } catch (Exception e) {
        }
        trigPin.low();

        //echoPin이 High가 될때까지 기다림
        while (echoPin.isLow()) {

        }
        //발신 시간을 저장
        startTime = System.nanoTime();

        //echoPin이 Low가 될때까지 기다림
        while (echoPin.isHigh()) {

        }
        endTime = System.nanoTime();

        //편도 시간(sec)
        double seconds = (endTime - startTime) / NANO_SECOND / 2;
        //거리(cm)
        int distance = (int) (seconds * SOUND_VALOCITY);

        //100이상 튀는 값이 있을 경우 다시 측정
        if (again == false && Math.abs(previousDistance - distance) > 100) {
            again = true;
            getDistance(); //dummy read
            distance = getDistance();
        } else {
            again = false;
        }

        //초과값 검사(2cm ~ 400cm)
        if (distance < 2) {
            distance = 2;
        } else if (distance > 400) {
            distance = 400;
        }
        previousDistance = distance;
        return distance;
    }

    //Method
    public static void main(String[] args) throws Exception {
        UltrasonicSensor us1 = new UltrasonicSensor(RaspiPin.GPIO_04, RaspiPin.GPIO_05);
        UltrasonicSensor us2 = new UltrasonicSensor(RaspiPin.GPIO_06, RaspiPin.GPIO_26);
        UltrasonicSensor us3 = new UltrasonicSensor(RaspiPin.GPIO_27, RaspiPin.GPIO_28);
//        UltrasonicSensor us4 = new UltrasonicSensor(RaspiPin.GPIO_24, RaspiPin.GPIO_25);
        UltrasonicSensor us5 = new UltrasonicSensor(RaspiPin.GPIO_22, RaspiPin.GPIO_23);

//        while (true) {
//            System.out.println("1 Sensor: " + us1.getDistance() + "cm");
//            System.out.println("2 Sensor: " + us2.getDistance() + "cm");
//            System.out.println("3 Sensor: " + us3.getDistance() + "cm");
//            System.out.println("5 Sensor: " + us5.getDistance() + "cm");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//
//            }
//        }
        Thread thread1 = new Thread(() -> {
            getValue("1", us1);
        });
        Thread thread2 = new Thread(() -> {
            getValue("2", us2);
        });
        Thread thread3 = new Thread(() -> {
            getValue("3", us3);
        });
//        Thread thread4 = new Thread(() -> {
//            getValue("4", us4);
//        });
        Thread thread5 = new Thread(() -> {
            getValue("5", us5);
        });
        thread1.start();
        thread2.start();
        thread3.start();
//        thread4.start();
        thread5.start();
    }

    private static void getValue(String name, UltrasonicSensor us) {
        while (true) {
            System.out.println(name + " Sensor: " + us.getDistance() + "cm");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }
}
