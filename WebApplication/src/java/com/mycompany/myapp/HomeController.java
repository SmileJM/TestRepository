package com.mycompany.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        System.out.println("home");
        return "home";
    }
    @RequestMapping("/html")
    public String html() {
        return "html";
        // 리턴 값이 .jsp 파일과 관계가 있음        
    }
    @RequestMapping("/css/exam01")
    public String cssExam01() {
        return "css/exam01";  
    }
}
