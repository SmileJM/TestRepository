package com.mycompany.myapp.controller;

import com.mycompany.myapp.service.Exam10ServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Exam10DIControllor {
    @Autowired
    private Exam10ServiceImple exam10Service;
    
    @RequestMapping("di/exam01")
    public String exam01() {        
        exam10Service.join();
        return "di/exam01";
    }
    
    @RequestMapping("di/exam02")
    public String exam02() {
        exam10Service.login();
        return "di/exam02";
    }
}
