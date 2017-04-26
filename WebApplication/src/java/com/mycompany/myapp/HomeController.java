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
    @RequestMapping("/css/exam02")
    public String cssExam02() {
        return "css/exam02";  
    }
    @RequestMapping("/css/exam03")
    public String cssExam03() {
        return "css/exam03";  
    }
    @RequestMapping("/javascript/exam01")
    public String javascriptExam01() {
        return "javascript/exam01";  
    }
    @RequestMapping("javascript/exam02")
    public String javascriptExam02() {
        return "javascript/exam02";
    }
    @RequestMapping("javascript/exam03")
    public String javascriptExam03() {
        return "javascript/exam03";
    }
    @RequestMapping("javascript/exam04")
    public String javascriptExam04() {
        return "javascript/exam04";
    }
}
