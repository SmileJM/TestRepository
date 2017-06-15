package com.mycompany.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/http")
public class HttpController {
	@RequestMapping("/exam01")
	public String exam01() {
		return "http/exam01";
	}

}
