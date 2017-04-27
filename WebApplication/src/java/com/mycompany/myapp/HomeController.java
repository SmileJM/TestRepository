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

    @RequestMapping("/javascript/exam02")
    public String javascriptExam02() {
        return "javascript/exam02";
    }

    @RequestMapping("/javascript/exam03")
    public String javascriptExam03() {
        return "javascript/exam03";
    }

    @RequestMapping("/javascript/exam04")
    public String javascriptExam04() {
        return "javascript/exam04";
    }

    @RequestMapping("/javascript/exam05")
    public String javascriptExam05() {
        return "javascript/exam05";
    }

    @RequestMapping("/javascript/exam06")
    public String javascriptExam06() {
        return "javascript/exam06";
    }

    @RequestMapping("/javascript/exam07")
    public String javascriptExam07() {
        return "javascript/exam07";
    }

    @RequestMapping("/javascript/exam08")
    public String javascriptExam08() {
        return "javascript/exam08";
    }

    @RequestMapping("/javascript/exam09")
    public String javascriptExam09() {
        return "javascript/exam09";
    }

    @RequestMapping("/javascript/exam10")
    public String javascriptExam10() {
        return "javascript/exam10";
    }

    @RequestMapping("/javascript/exam11")
    public String javascriptExam11() {
        return "javascript/exam11";
    }

    @RequestMapping("/bootstrap/exam01")
    public String bootstrapExam01() {
        return "bootstrap/exam01";
    }

    @RequestMapping("/bootstrap/exam02")
    public String bootstrapExam02() {
        return "bootstrap/exam02";
    }

    @RequestMapping("/bootstrap/exam03")
    public String bootstrapExam03() {
        return "bootstrap/exam03";
    }

    @RequestMapping("/jquery/exam01")
    public String jqueryExam01() {
        return "jquery/exam01";
    }

    @RequestMapping("/jquery/exam02")
    public String jqueryExam02() {
        return "jquery/exam02";
    }
    @RequestMapping("/jquery/exam03")
    public String jqueryExam03() {
        return "jquery/exam03";
    }
    @RequestMapping("/jquery/exam04")
    public String jqueryExam04() {
        return "jquery/exam04";
    }
    @RequestMapping("/jquery/exam04_html_fragment")
    public String jqueryExam04_html_fragment() {
        return "jquery/exam04_html_fragment";
    }
    @RequestMapping("/jquery/exam04_json")
    public String jqueryExam04_json() {
        return "jquery/exam04_json";
    }
}
