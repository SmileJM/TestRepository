package com.mycompany.myapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@Controller
public class FacebookController {
	static final String FACEBOOK_CLIENT_ID = "322630751518640";
	static final String FACEBOOK_CLIENT_SECRET_KEY = "b8f941a626d35dbc4ca5756c66d7984f";
	static final String CONTEXT_ROOT = "Facebook1";

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/fb/login")
	public void login(HttpServletResponse response) throws IOException {
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(FACEBOOK_CLIENT_ID,
				FACEBOOK_CLIENT_SECRET_KEY);

		connectionFactory.setScope("public_profile, email");

		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://localhost:8080/" + CONTEXT_ROOT + "/fb/callback");
		params.setScope("public_profile, email");
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();

		String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);
		System.out.println("getScope: " + connectionFactory.getScope());
		System.out.println("params: " + params);
		System.out.println("authorizeUrl: " + authorizeUrl);
		response.sendRedirect(authorizeUrl);
	}

	@RequestMapping("/fb/callback")
	public String callback(@RequestParam("code") String authorizationCode, HttpServletRequest request) {
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(FACEBOOK_CLIENT_ID,
				FACEBOOK_CLIENT_SECRET_KEY);
		
		connectionFactory.setScope("public_profile, email");
		
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(authorizationCode,
				"http://localhost:8080/" + CONTEXT_ROOT + "/fb/callback", null);
		
		String token = accessGrant.getAccessToken();

		request.getSession().setAttribute("facebookToken", token);
		return "redirect:/fb";
	}

	@RequestMapping("/fb")
	public String fb(HttpServletRequest request, Model model) {
		String accessToken = (String) request.getSession().getAttribute("facebookToken");
		System.out.println(accessToken);
		Facebook facebook = new FacebookTemplate(accessToken);
		System.out.println("uri: " + facebook.getBaseGraphApiUrl());

		String[] fields = { "email", "last_name", "first_name" };

		User profile = facebook.fetchObject("me", User.class, fields);

		System.out.println(profile.getFirstName());
		System.out.println(profile.getLastName());
		System.out.println(profile.getEmail());
		model.addAttribute("first", profile.getFirstName());
		model.addAttribute("last", profile.getLastName());
		model.addAttribute("email", profile.getEmail());
		model.addAttribute("gender", profile.getGender());
		model.addAttribute("birthday", profile.getBirthday());
		model.addAttribute("id", profile.getId());
		model.addAttribute("name", profile.getName());
		model.addAttribute("email", profile.getEmail());

		if (facebook.isAuthorized()) {
			return "fb";
		} else {
			return "redirect:/fb/login";
		}
	}

}
