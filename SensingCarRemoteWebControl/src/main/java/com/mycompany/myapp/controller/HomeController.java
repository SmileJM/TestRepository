package com.mycompany.myapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private String ipAddress = "192.168.3.50";

	@RequestMapping("/")
	public String home(Model model) {
		// 공통으로 사용하는 변수 초기화
		CoapClient coapClient = new CoapClient();
		CoapResponse coapResponse = null;
		JSONObject jsonObject = null;
		String json = null;

		// ------------------------------------------------------------------------
		// camera
		// ------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient.setURI("coap://" + ipAddress + "/camera");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		model.addAttribute("leftright", jsonObject.getString("leftright"));
		model.addAttribute("updown", jsonObject.getString("updown"));
		// ------------------------------------------------------------------------
		// rgbled
		// ------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient.setURI("coap://" + ipAddress + "/rgbled");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		model.addAttribute("red", jsonObject.getString("red"));
		model.addAttribute("green", jsonObject.getString("green"));
		model.addAttribute("blue", jsonObject.getString("blue"));
		// ------------------------------------------------------------------------
		// laseremitter
		// ------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient.setURI("coap://" + ipAddress + "/laseremitter");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		model.addAttribute("laseremitterStatus", jsonObject.getString("status"));
		// ------------------------------------------------------------------------
		// buzzer
		// ------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient.setURI("coap://" + ipAddress + "/buzzer");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		model.addAttribute("buzzerStatus", jsonObject.getString("status"));
		// ------------------------------------------------------------------------
		// ultrasonicsensor
		// ------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient.setURI("coap://" + ipAddress + "/ultrasonicsensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		model.addAttribute("ultrasonicsensorAngle", jsonObject.getString("angle"));
		model.addAttribute("ultrasonicsensorDistance", jsonObject.getString("distance"));
		// ------------------------------------------------------------------------
		// lcd
		// ------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient.setURI("coap://" + ipAddress + "/lcd");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		model.addAttribute("lcdline0", jsonObject.getString("line0"));
		model.addAttribute("lcdline1", jsonObject.getString("line1"));
		// ------------------------------------------------------------------------
		// fronttire
		// ------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient.setURI("coap://" + ipAddress + "/fronttire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		model.addAttribute("fronttireAngle", jsonObject.getString("angle"));
		// ------------------------------------------------------------------------
		// backtire
		// ------------------------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();

		coapClient.setURI("coap://" + ipAddress + "/backtire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();

		jsonObject = new JSONObject(json);
		model.addAttribute("backtireDirection", jsonObject.getString("direction"));
		model.addAttribute("backtireSpeed", jsonObject.getString("speed"));

		model.addAttribute("cameraUrl", "http://" + ipAddress + ":50001?action=stream");
		coapClient.shutdown();

		return "controlpanel";
	}

	@RequestMapping("/camera")
	public void camera(String command, String leftright, String updown, HttpServletResponse response)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("leftright", leftright);
		jsonObject.put("updown", updown);
		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/camera");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}

	@RequestMapping("/rgbled")
	public void rgbled(String command, String red, String green, String blue, HttpServletResponse response)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("red", red);
		jsonObject.put("green", green);
		jsonObject.put("blue", blue);
		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/rgbled");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}

	@RequestMapping("/laseremitter")
	public void laseremitter(String command, String status, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/laseremitter");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}

	@RequestMapping("/buzzer")
	public void buzzer(String command, String status, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/buzzer");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}

	@RequestMapping("/ultrasonicsensor")
	public void ultrasonicsensor(String command, String distance, String angle, HttpServletResponse response)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("angle", angle);
		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/ultrasonicsensor");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}

	@RequestMapping("/lcd")
	public void lcd(String command, String line0, String line1, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("line0", line0);
		jsonObject.put("line1", line1);
		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/lcd");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}

	@RequestMapping("/fronttire")
	public void fronttire(String command, String angle, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("angle", angle);
		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/fronttire");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}

	@RequestMapping("/backtire")
	public void backtire(String command, String direction, String speed, HttpServletResponse response)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("direction", direction);
		jsonObject.put("speed", speed);
		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + ipAddress + "/backtire");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
}
