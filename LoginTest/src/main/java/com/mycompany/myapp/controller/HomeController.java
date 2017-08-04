package com.mycompany.myapp.controller;

import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
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
	static final String FACEBOOK_CLIENT_ID = "322630751518640";
	static final String FACEBOOK_CLIENT_SECRET_KEY = "b8f941a626d35dbc4ca5756c66d7984f";
	// static final String REDIRECT_URL =
	// "https://www.facebook.com/connect/login_success.html";
	static final String REDIRECT_URL = "http://localhost:8080/LoginTest";

	@RequestMapping("/")
	public String home() {
		logger.info("home page");
		return "home";
	}

	@RequestMapping("/connect/login")
	public String facebookLogin() {
		logger.info("login page");

		return "connect/login";
	}

	@RequestMapping(value = "/facebookSignin.do")
	public String getfacebookSigninCode(HttpSession session) {
		logger.info("facebookSignin");

		String facebookUrl = "https://www.facebook.com/v2.8/dialog/oauth?" + 
				"client_id=" + FACEBOOK_CLIENT_ID +
				"&redirect_uri=http://localhost:8080/LoginTest/facebookAccessToken.do" +
//				"&scope=public_profile,email";
				"&scope=email";

		return "redirect:" + facebookUrl;
	}

	@RequestMapping(value = "/facebookAccessToken.do")
	public String getFacebookSignIn(String code, HttpSession session, String state) throws Exception {
		logger.info("facebookAccessToken / code : " + code);

		String accessToken = requesFaceBooktAccesToken(session, code);
		facebookUserDataLoadAndSave(accessToken, session);

		return "redirect:main.do";
	}

	private String requesFaceBooktAccesToken(HttpSession session, String code) throws Exception {
		String facebookUrl = "https://graph.facebook.com/v2.8/oauth/access_token?" + 
				"client_id=" + FACEBOOK_CLIENT_ID	+ 
				"&redirect_uri=" + REDIRECT_URL + 
				"&client_secret=" + FACEBOOK_CLIENT_SECRET_KEY + 
				"&code=" + code;

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(facebookUrl);
		logger.info("getRequest: " + getRequest);
		String rawJsonString = httpClient.execute(getRequest, new BasicResponseHandler());

		logger.info("facebookAccessToken / raw json : " + rawJsonString);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);
		String faceBookAccessToken = (String) jsonObject.get("access_token");
		logger.info("facebookAccessToken / accessToken : " + faceBookAccessToken);

		session.setAttribute("faceBookAccessToken", faceBookAccessToken);

		return faceBookAccessToken;
	}

	private void facebookUserDataLoadAndSave(String accessToken, HttpSession session) throws Exception {
		String facebookUrl = "https://graph.facebook.com/me?" + "access_token=" + accessToken
				+ "&fields=id,name,email,picture";

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(facebookUrl);
		String rawJsonString = client.execute(getRequest, new BasicResponseHandler());
		logger.info("facebookAccessToken / rawJson!  : " + rawJsonString);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);
		logger.info("facebookUserDataLoadAndSave / raw json : " + jsonObject);

		/* 가져온 데이터를 서비스에 맞춰 가공하는 로직 */

	}

}
