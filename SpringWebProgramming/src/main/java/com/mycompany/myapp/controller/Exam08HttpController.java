package com.mycompany.myapp.controller;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/http")
public class Exam08HttpController {

    @RequestMapping(value = "/exam01", method = RequestMethod.GET)
    public String exam01Get() {
        System.out.println("exam01() ... get");
        return "http/exam01";
    }

    @RequestMapping(value = "/exam01", method = RequestMethod.POST)
    public String exam01Post() {
        System.out.println("exam01() ... post");
        return "http/exam01";
    }

    @RequestMapping("/exam02")
    public String exam02(HttpServletRequest request, Model model) {
        // HttpServletRequest 안에는 요청 http 에 대한 모든 내용이 있음
        // 아래 나오는 내용은 스프링 전에 사용하던 고전적 방식임
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String type = request.getParameter("type");
        int bno = Integer.parseInt(request.getParameter("bno"));
        String[] hobby = request.getParameterValues("hobby");

        String userAgent = request.getHeader("user-agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            userAgent = "IE11 이하 브라우저";
        } else if (userAgent.contains("Edge")) {
            userAgent = "Edge 브라우저";
        } else if (userAgent.contains("Chrome")) {
            userAgent = "Chrome 브라우저";
        }

        model.addAttribute("method", method);
        model.addAttribute("uri", uri);
        model.addAttribute("queryString", queryString);
        model.addAttribute("type", type);
        model.addAttribute("bno", bno);
        model.addAttribute("hobby", hobby);
        model.addAttribute("userAgent", userAgent);

        return "http/exam02";
    }

    // 스프링에서의 사용 방법
    @RequestMapping("/exam03")
    public String exam03(
        // 파라미터와 변수 이름이 같을 때 값을 대입함
        @RequestParam String type,
        @RequestParam("bno") int boardNo,
        String[] hobby,
        @RequestHeader("User-Agent") String userAgent,
        Model model) {
        model.addAttribute("type", type);
        model.addAttribute("bno", boardNo);
        model.addAttribute("hobby", hobby);
        model.addAttribute("userAgent", userAgent);
        return "http/exam02";
    }
}
