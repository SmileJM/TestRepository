package com.mycompany.myapp.social.facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FBConnection {
	private static final Logger logger = LoggerFactory.getLogger(FBConnection.class);
	
	public static final String FB_APP_ID = "322630751518640";
	public static final String FB_APP_SECRET = "b8f941a626d35dbc4ca5756c66d7984f";
	public static final String REDIRECT_URI = "http://localhost:8080/FacebookLogin/connect/fbhome";

	static String accessToken = "";

	public String getFBAuthUrl() {
		logger.info("success");
		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id=" + FBConnection.FB_APP_ID
					+ "&redirect_uri=" + URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8") + "&scope=email";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.info(fbLoginUrl);
//		String code = "AQCTa41vE8wg9dKJWX5AtNMY34MF0dko97D7oHjnIUaf7uRHathQ3-b6dypF_3NoUCDR_mp6c0pcP4nsUiVVg9ZnXoLdicrUHdmIDPO1meeTFtO2DbQQuLqupQxj9BzJ0p5ba2HrwP--PS7aUe2zr_EG2Oztyp7_eqmzfIKsi0yl0J2ucezCjkCIqkxh249AkkQ4xu7i0qOpbi_mVQIQoOoexgVi6lqCgBOs011NsWE9YRAspn99rhUSrx7GyeMrWPE2AJ_o7T7RmGPkfmpCxFvy-jr3mGZQ3WgpbOGnbT7CsaEB7GQ55N1dwNyffekLrZsjc6hAdivlvPjNpbXCZrEF";
//		getAccessToken(code);
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code) {
		logger.info("success");
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/access_token?" + "client_id=" + FBConnection.FB_APP_ID
					+ "&redirect_uri=" + URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8") + "&client_secret="
					+ FB_APP_SECRET + "&code=" + code;
			logger.info("try");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		logger.info("success");
		if ("".equals(accessToken)) {
			URL fbGraphURL;
			try {
				fbGraphURL = new URL(getFBGraphUrl(code));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
			URLConnection fbConnection;
			StringBuffer b = null;
			try {
				fbConnection = fbGraphURL.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(fbConnection.getInputStream()));
				String inputLine;
				b = new StringBuffer();
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Unable to connect with Facebook " + e);
			}

			accessToken = b.toString();
			if (accessToken.startsWith("{")) {
				throw new RuntimeException("ERROR: Access Token Invalid: " + accessToken);
			}
		}
		return accessToken;
	}
}
