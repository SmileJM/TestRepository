package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.laser.LaserEmitter;
import hardware.lcd.LCD1602;
import hardware.led.RgbLedPWM;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.GasSensor;
import hardware.sensor.PhotoresistorSensor;
import hardware.sensor.ThermistorSensor;
import hardware.sensor.TrackingSensor;
import hardware.sensor.UltrasonicSensor;
import java.text.SimpleDateFormat;

public class SensingCarAllTest {

    public static void main(String[] args) throws Exception {
        ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_24);
        LaserEmitter laserEmitter = new LaserEmitter(RaspiPin.GPIO_25);
        LCD1602 lcd1602 = new LCD1602(0x27);
        lcd1602.write(0, 0, "Current Time");
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        RgbLedPWM test = new RgbLedPWM(RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06);
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
    }
}
