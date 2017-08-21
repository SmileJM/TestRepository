package com.mycompany.myapp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.service.MemberService;
import com.mycompany.myapp.websocket.GyroSensorHandler;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "member", "log", "id" })
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	String log = "log";

	private static String mqttId = "";

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
	public String fb(HttpServletRequest request, Model model) throws IOException {
		String accessToken = (String) request.getSession().getAttribute("facebookToken");
		Facebook facebook = new FacebookTemplate(accessToken);

		if (facebook.isAuthorized()) {
			// 페이스북에서 프로필을 읽어온다.
			User profile = facebook.fetchObject("me", User.class, "name,email,cover,picture");

			// 프로필을 모델로 전송
			model.addAttribute("profile", profile);

			// 멤버객체 생성
			member = new Member();

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
				System.out.println("login member.getMid(): " + member.getMid() );
				// Mqtt
				myClientId = MqttClient.generateClientId();
				topicRequest = "/" + member.getMid() + "/gyro/request";
				topicResponse = "/" + member.getMid() + "/gyro/response";

				String url = "tcp://106.253.56.122:1883";
				try {
					mqttClient = new MqttClient(url, myClientId);
					mqttClient.setCallback(callback);
					mqttClient.connect();
					subscribe();
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return "main";
			}
		} else {
			return "redirect:/fb/login";
		}
	}

	private List<WebSocketSession> list = new Vector<>();
	private String myClientId;
	MqttClient mqttClient;
	String topicRequest;
	String topicResponse;
	Member member;
	private MqttCallback callback = new MqttCallback() {

		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {

		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
			String json = new String(mm.getPayload());
			JSONObject jsonObject = new JSONObject(json);

			jsonObject.put("mid", member.getMid());
//			json = jsonObject.toString();
			
			double yawAngle = Double.parseDouble(jsonObject.getString("yawAngle"));
			double pitchAngle = Double.parseDouble(jsonObject.getString("pitchAngle"));
			double rollAngle = Double.parseDouble(jsonObject.getString("rollAngle"));
			jsonObject.put("time", getUTCTime(new Date().getTime()));
			jsonObject.put("yawAngle", yawAngle);
			jsonObject.put("pitchAngle", pitchAngle);
			jsonObject.put("rollAngle", rollAngle);
			json = jsonObject.toString();			

//			GyroSensorHandler.getInstance().send("mid", member.getMid());
			GyroSensorHandler.getInstance().send("json", json);		

		}

		@Override
		public void connectionLost(Throwable thrwbl) {
			try {
				close();
			} catch (MqttException ex) {
				ex.printStackTrace();
			}
		}
	};

	public long getUTCTime(long localTime) {
		long utcTime = 0;
		TimeZone tz = TimeZone.getDefault();
		try {
			int offset = tz.getOffset(localTime);
			utcTime = localTime + offset;
		} catch (Exception e) {
		}
		return utcTime;
	}

	public void close() throws MqttException {
		if (mqttClient != null) {
			mqttClient.disconnect();
			mqttClient.close();
			mqttClient = null;
		}
	}

	public void subscribe() throws MqttException {
		mqttClient.subscribe(topicResponse);
	}

	@RequestMapping("/")
	public String homeGet(Model model) {
		return "main";
	}

	public static String getMqttId() {
		return mqttId;
	}
}
