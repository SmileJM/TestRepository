package com.mycompany.myapp.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.GasSensorDao;
import com.pi4j.io.gpio.RaspiPin;

import hardware.converter.PCF8591;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.GasSensor;
import hardware.sensor.UltrasonicSensor;

@Component
public class SensingCarServiceImpl implements SensingCarService {
	@Autowired
	private GasSensorDao gasSensorDao;
	
	// 하드웨어 PWM
	private PCA9685 pca9685;

	// ADC
	private PCF8591 gasSensorPCF8591;

	// 초음파 센서 관련 필드
	private SG90ServoPCA9685Duration ultrasonicSensorServo;
	private UltrasonicSensor ultrasonicSensor;

	// GasSensor 관련 필드
	private GasSensor gasSensor;

	// 이 객체가 생성된 후에 실행하는...
	// 생성자에서 초기할 수 없는 것들을 위해서 사용 가능
	@PostConstruct
	public void init() throws Exception {
		pca9685 = PCA9685.getInstance();		
		
		ultrasonicSensorServo = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);
		ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		
		gasSensorPCF8591 = new PCF8591(0x48, PCF8591.AIN2);
		gasSensor = new GasSensor(gasSensorPCF8591, RaspiPin.GPIO_23);
	}

	@Override
	public void changeUltrasonicSensorAngle(int angle) throws Exception {
		ultrasonicSensorServo.setAngle(angle);
	};

	@Override
	public int getUltrasonicSensorDistance() throws Exception {
		int distance = ultrasonicSensor.getDistance();
		return distance;
	}

	@Override
	public double getGasSensorValue() throws Exception {
		// 센서로부터 측정 값 받기
		double value = gasSensor.getValue();
		
		// DB에 저장
		// 이름이 같은것이 두개가 있어서 풀패키지명을 포함하여 생성
		com.mycompany.myapp.dto.GasSensor gasSensorBean = new com.mycompany.myapp.dto.GasSensor();
		gasSensorBean.setGvalue(value);
		gasSensorDao.insert(gasSensorBean);
		return value;
	}
}
