package com.mycompany.myapp.controller;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class GyroCotroller {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/gyroTest")
	public String gyroTest() throws MqttException{
		logger.info("");
		return "gyroTest";
	}

}
