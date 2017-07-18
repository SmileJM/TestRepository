package com.mycompany.myapp.controller;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private String ipAddress = "192.168.3.113";

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/mouse")
	public void mouse() throws Exception {
		CoapClient coapClient = new CoapClient();
		CoapResponse coapResponse = null;
		JSONObject jsonObject = null;
		String json = null;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		while (true) {
			jsonObject = new JSONObject();
			jsonObject.put("command", "status");
			json = jsonObject.toString();

			coapClient.setURI("coap://" + ipAddress + "/mouse");
			coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
			json = coapResponse.getResponseText();

			jsonObject = new JSONObject(json);

			double doubleT = Double.parseDouble(jsonObject.getString("temperature"));
			int xValue = Integer.parseInt(jsonObject.getString("acclX"));
			int yValue = Integer.parseInt(jsonObject.getString("acclX"));
			int zValue = Integer.parseInt(jsonObject.getString("acclX"));

			int height = screen.height;
			int width = screen.width;

			Robot robot = new Robot();
			System.out.println(height);
			System.out.println(width);

			xValue /= 20;
			if (xValue < -height / 2) {
				xValue = height / 2500;
			} else if (xValue > height / 2) {
				xValue = height / 2;
			}
			xValue += height / 2;

			robot.mouseMove(xValue, 0);
			Thread.sleep(10);

			System.out.println("xValue: " + xValue + "\tyValue: " + yValue + "\tzValue: " + zValue);
		}

	}

}
