package com.mycompany.myapp.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.service.MemberService;
import com.mycompany.myapp.websocket.GyroSensorHandler;
import com.mycompany.myapp.websocket.IfraredraySensorHandler;
import com.mycompany.myapp.websocket.UltrasonicSensorHandler;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "member", "log", "id", "mid" })
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	String log = "log";

	private static String mqttId = "";

	@Autowired
	private ApplicationContext applicationContext;

	@Resource(name = "memberServiceImpl")
	private MemberService service;

	@RequestMapping("fb/login")
	public void login(HttpServletResponse response) throws IOException {
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("1541339359250690",
				"9477d672e7f7aec8cc02f4c7a17f3552");
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://localhost:8080/iot1_motion/fb/callback");
		params.setScope("public_profile, email");
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);

		response.sendRedirect(authorizeUrl);
	}

	@RequestMapping("fb/callback")
	public String callback(@RequestParam("code") String authorizationCode, HttpServletRequest request) {
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("1541339359250690",
				"9477d672e7f7aec8cc02f4c7a17f3552");

		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(authorizationCode,
				"http://localhost:8080/iot1_motion/fb/callback", null);
		String token = accessGrant.getAccessToken();
		request.getSession().setAttribute("facebookToken", token);

		return "redirect:/fb";
	}

	@RequestMapping(value = "/fb")
	public String fb(HttpServletRequest request, Model model, HttpSession session) throws IOException, MqttException {
		String accessToken = (String) request.getSession().getAttribute("facebookToken");
		Facebook facebook = new FacebookTemplate(accessToken);

		if (facebook.isAuthorized()) {
			// 페이스북에서 프로필을 읽어온다.
			User profile = facebook.fetchObject("me", User.class, "name,email,cover,picture");

			// 프로필을 모델로 전송
			model.addAttribute("profile", profile);

			// 멤버객체 생성
			Member member = new Member();

			// 필드에 저장
			if (profile.getEmail() == null) {
				member.setMemail("");
			} else {
				member.setMemail(profile.getEmail());
			}
			member.setMname(profile.getName());
			member.setMlevel("1");
			member.setMid(profile.getId());
			mqttId = profile.getName();

			// 전송
			model.addAttribute("member", member);
			member = service.getMember(member.getMid());

			// 1. 회원가입이 안되있는 경우
			if (member == null) {
				return "join";
			}
			// 2. 회원가입이 되어있는 경우
			else {
				log = "login";
				model.addAttribute("log", log);
				model.addAttribute("member", member);
				model.addAttribute("mid", member.getMid());
				
				// Mqtt
//				MqttClientTest mqtt = new MqttClientTest();
				System.out.println("session: " + session);
//				mqtt.getMqtt(session);
				String url = "tcp://106.253.56.122:1883";
				String myClientId = MqttClient.generateClientId();
				MqttClient mqttClient = new MqttClient(url, myClientId);
				String mid =  member.getMid();
				String json1 = "";
				System.out.println("myClientId: " + myClientId);
				mqttClient.setCallback(new MqttCallback() {
					@Override
					public void deliveryComplete(IMqttDeliveryToken token) {
						logger.info("");
					}
					@Override
					public void messageArrived(String topic, MqttMessage mm) throws Exception {	
						logger.info("");						
						String mid = (String) session.getAttribute("mid");
					
						String json = new String(mm.getPayload());						
						GyroSensorHandler gyroSensorHandler = (GyroSensorHandler) applicationContext.getBean("gyroSensorHandler");
						IfraredraySensorHandler ifraredraySensorHandler = (IfraredraySensorHandler) applicationContext.getBean("ifraredraySensorHandler");
						UltrasonicSensorHandler ultrasonicSensorHandler = (UltrasonicSensorHandler) applicationContext.getBean("ultrasonicSensorHandler");
						System.out.println("mid: " + mid);
						System.out.println(" json" +  json);
						System.out.println(" topic" +  topic);
						if(topic.indexOf("gyro")>0){
							System.out.println("gyro");
							gyroSensorHandler.sendMessage(mid, json, "gyro");
						} else if(topic.indexOf("ifraredray")>0){
							ifraredraySensorHandler.sendMessage(mid, json, "ifraredray");
							System.out.println("ifraredray");
						}else if(topic.indexOf("ultrasonic")>0){
							ultrasonicSensorHandler.sendMessage(mid, json, "ultrasonic");
							System.out.println("ultrasonic");
						}
//						gyroSensorHandler.sendMessage(mid, json);
//						JSONObject jsonObject = new JSONObject(json);
						
//						if( jsonObject.getString("yawAngle") != null) {
//							gyroSensorHandler.sendMessage(mid, json);
//						} else if ( jsonObject.getString("yawAngle") != null) {
//							
//						}
					}
					@Override
					public void connectionLost(Throwable cause) {
						logger.info("");
						try {
							if(mqttClient != null) {
								mqttClient.disconnect();
								mqttClient.close();
//								mqttClient = null;
							}
						} catch (MqttException ex) {
							ex.printStackTrace();
						}
					}
				});
				mqttClient.connect();
				mqttClient.subscribe("/" + mid + "/#");
				session.setAttribute("mqttClient", mqttClient);
				
				return "main";
			}
		} else {
			return "redirect:/fb/login";
		}
	}

	
	@RequestMapping("/")
	public String homeGet(Model model) {
		return "main";
	}

	public static String getMqttId() {
		return mqttId;
	}
}
