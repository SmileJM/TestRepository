package com.mycompany.myapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.myapp.dto.Exam13Member;

@Controller
@SessionAttributes({ "name1", "name2", "member" })
public class Exam13CookieAndSessionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam13CookieAndSessionController.class);

	@RequestMapping("/cookie/exam01")
	public String exam01(HttpServletResponse response) throws UnsupportedEncodingException {
		// 쿠키 생성
		Cookie cookie1 = new Cookie("name1", "hongkildong");
		String name2 = "홍길동";
		name2 = URLEncoder.encode(name2, "UTF-8"); // URL 인코딩이라고 함
		// UTF-8 로 바이트 배열을 만든 후 16진수 아스키 코드로 만듬
		Cookie cookie2 = new Cookie("name2", name2);
		cookie2.setMaxAge(10);
		// cookie2.setMaxAge(30 * 24 * 60 * 60);

		// 쿠키를 응답 헤더에 추가
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		return "cookie/exam01";
	}
	/*
	 * @RequestMapping("/cookie/exam02") public String exam02(HttpServletRequest
	 * request, Model model) throws UnsupportedEncodingException { // 스프링을 사용하지
	 * 않을 때의 방법
	 * 
	 * String name1 = null; String name2 = null; Cookie[] cookies =
	 * request.getCookies(); for (Cookie cookie : cookies) { if
	 * (cookie.getName().equals("name1")) { name1 = cookie.getValue(); } else
	 * if(cookie.getName().equals("name2")) { name2 = cookie.getValue(); name2 =
	 * URLDecoder.decode(name2, "UTF-8"); } } model.addAttribute("name1" ,
	 * name1); model.addAttribute("name2" , name2); return "cookie/exam02"; }
	 */

	@RequestMapping("/cookie/exam02")
	public String exam02(@CookieValue(defaultValue = "") String name1, @CookieValue(defaultValue = "") String name2,
			Model model) {
		// 스프링을 사용하였을 때, 자동으로 디코딩도 됨
		model.addAttribute("name1", name1);
		model.addAttribute("name2", name2);
		return "cookie/exam02";
	}

	@RequestMapping("/cookie/exam03")
	public String exam03(HttpServletResponse response) {
		// 쿠키 삭제시, 마치 쿠키를 만드는 것처럼 하고 값을 주지 않음, maxage 도 0
		Cookie cookie1 = new Cookie("name1", "");
		Cookie cookie2 = new Cookie("name2", "");

		// 쿠키 삭제
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);

		response.addCookie(cookie1);
		response.addCookie(cookie2);

		return "redirect:/";
	}

	// @RequestMapping("/session/exam04")
	// public String exam04(HttpSession session) { // 세션에 문자열 정보를 저장
	// session.setAttribute("name1", "Hongkildong");
	// session.setAttribute("name2", "홍길동"); // 세션에 객체를 저장
	// Exam13Member member = new Exam13Member();
	// member.setMname("스프링");
	// session.setAttribute("member", member);
	//
	// return "redirect:/";
	// }

	@RequestMapping("/session/exam04")
	public String exam04(Model model) {
		model.addAttribute("name1", "Hongkildong");
		model.addAttribute("name2", "홍길동");
		// 세션에 객체를 저장
		Exam13Member member = new Exam13Member();
		member.setMname("스프링");
		model.addAttribute("member", member);
		return "redirect:/";
	}

	// @RequestMapping("/session/exam05")
	// public String exam05(HttpSession session) {
	// String name1 = (String) session.getAttribute("name1");
	// String name2 = (String) session.getAttribute("name2");
	// Exam13Member member = (Exam13Member) session.getAttribute("member");
	//
	// LOGGER.debug(name1);
	// LOGGER.debug(name2);
	// LOGGER.debug(member.getMname());
	//
	// return "session/exam05";
	// }

	@RequestMapping("/session/exam05")
	public String exam05(@ModelAttribute String name1, // model 을 통해서 저장된
														// attribute를 찾아오라는 것
			@ModelAttribute String name2, @ModelAttribute Exam13Member member) {
		LOGGER.debug(name1);
		LOGGER.debug(name2);
		LOGGER.debug(member.getMname());
		return "session/exam05";
	}

	// @RequestMapping("/session/exam06")
	// public String exam06(HttpSession session) {
	// // 세션에서 작제해도 Model 에는 남아있을 수 있다.
	// // @SessionAttributes 대신 HttpSession 만 이용할 경우에 사용
	// session.removeAttribute("name1");
	// session.removeAttribute("name2");
	// session.removeAttribute("member");
	// return "redirect:/";
	// }
	@RequestMapping("/session/exam06")
	public String exam06(SessionStatus sessionStatus) {
		// 세션에 있는 모든 정보를 삭제
		// @SessionAttributes 를 사용할 경우 이용
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
