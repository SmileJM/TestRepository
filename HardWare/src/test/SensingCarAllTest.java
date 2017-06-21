package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.laser.LaserEmitter;
import hardware.lcd.LCD1602;
import hardware.led.RgbLedPWM;
import hardware.motor.DCMotor;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.GasSensor;
import hardware.sensor.PhotoresistorSensor;
import hardware.sensor.ThermistorSensor;
import hardware.sensor.TrackingSensor;
import hardware.sensor.UltrasonicSensor;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SensingCarAllTest {

    public static void main(String[] args) throws Exception {
        ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_24);
        LaserEmitter laserEmitter = new LaserEmitter(RaspiPin.GPIO_25);
        LCD1602 lcd1602 = new LCD1602(0x27);

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        RgbLedPWM led = new RgbLedPWM(RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06);
        PCA9685 pca9685 = PCA9685.getInstance();
        SG90ServoPCA9685Duration servo1 = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_00);
        SG90ServoPCA9685Duration servo2 = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);
        SG90ServoPCA9685Duration servo3 = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_14);
        SG90ServoPCA9685Duration servo4 = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_15);
        PCF8591 gasSensorPCF8591 = new PCF8591(0x48, PCF8591.AIN2);
        GasSensor gasSensor = new GasSensor(gasSensorPCF8591, RaspiPin.GPIO_23);
        PCF8591 photoresistorPCF8591 = new PCF8591(0x48, PCF8591.AIN0);
        PhotoresistorSensor photoresistorSensor = new PhotoresistorSensor(photoresistorPCF8591);
        PCF8591 thermistorSensorPCF8591 = new PCF8591(0x48, PCF8591.AIN1);
        ThermistorSensor thermistorSensor = new ThermistorSensor(thermistorSensorPCF8591);
        TrackingSensor trackingSensor = new TrackingSensor(RaspiPin.GPIO_26);
        UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
        DCMotor motorA = new DCMotor(RaspiPin.GPIO_00, RaspiPin.GPIO_01, pca9685, PCA9685.PWM_05);
        DCMotor motorB = new DCMotor(RaspiPin.GPIO_02, RaspiPin.GPIO_03, pca9685, PCA9685.PWM_04);

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 30; i <= 150; i += 10) {
                        servo1.setAngle(i);
                        servo2.setAngle(i);
                        servo3.setAngle(i);
                        servo4.setAngle(i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                        }
                    }
                    for (int i = 150; i >= 30; i -= 10) {
                        servo1.setAngle(i);
                        servo2.setAngle(i);
                        servo3.setAngle(i);
                        servo4.setAngle(i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            }

        };
        thread1.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int gasValue = (int) gasSensor.getValue();
                        int photoresistorValue = (int) photoresistorSensor.getValue();
                        int thermistorValue = (int) thermistorSensor.getValue();
                        int ultrasonicValue = (int) ultrasonicSensor.getDistance();

                        System.out.println("[가스센서]: 　" + gasValue);
                        System.out.println("[조도센서]: 　" + photoresistorValue);
                        System.out.println("[온도센서]: 　" + thermistorValue);
                        System.out.println("[초음파센서]: " + ultrasonicValue);
                        System.out.println("-----------------------------------------");

                        Thread.sleep(2000);
                    } catch (Exception ex) {
                    }
                }
            }

        };
        thread2.start();

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                while (true) {
//                    activeBuzzer.on();
                    laserEmitter.shot();
                    lcd1602.write(0, 0, "Smile");
                    Date date = new Date();
                    lcd1602.write(1, 0, formatter.format(date));
                    led.ledColorSet(255, 0, 255);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    activeBuzzer.off();
                    laserEmitter.stop();
                    lcd1602.clear();
                    led.ledColorSet(0, 255, 0);
                             try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        thread3.start();
        Thread thread4 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    motorA.forward();
                    motorB.forward();
                    for (int i = 500; i < 2000; i += 100) {
                        try {
                            motorA.setSpeed(i);
                            motorB.setSpeed(i);
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                        }
                    }
                    for (int i = 2000; i > 500; i -= 100) {
                        try {
                            motorA.setSpeed(i);
                            motorB.setSpeed(i);
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                        }
                    }
                    motorA.backward();
                    motorB.backward();
                    for (int i = 500; i < 2000; i += 100) {
                        try {
                            motorA.setSpeed(i);
                            motorB.setSpeed(i);
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                        }
                    }
                    for (int i = 2000; i > 500; i -= 100) {
                        try {
                            motorA.setSpeed(i);
                            motorB.setSpeed(i);
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            }
        };
        thread4.start();
    }
}
