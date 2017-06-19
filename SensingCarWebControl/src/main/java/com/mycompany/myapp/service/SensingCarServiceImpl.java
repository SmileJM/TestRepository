package com.mycompany.myapp.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.pi4j.io.gpio.RaspiPin;

import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.UltrasonicSensor;

@Component
public class SensingCarServiceImpl implements SensingCarService {

	private PCA9685 pca9685;

	// 이 객체가 생성된 후에 실행하는...
	// @PostConstruct
	// public void init() throws Exception {
	// pca9685 = PCA9685.getInstance();
	// }

	@Override
	public void changeUltrasonicSensorAngle(int angle) throws Exception {
		pca9685 = PCA9685.getInstance();
		SG90ServoPCA9685Duration servo = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);
		servo.setAngle(angle);
	};

	@Override
	public int getUltrasonicSensorDistance() throws Exception {
		pca9685 = PCA9685.getInstance();
		UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		int distance = ultrasonicSensor.getDistance();
		return distance;
	}

}
