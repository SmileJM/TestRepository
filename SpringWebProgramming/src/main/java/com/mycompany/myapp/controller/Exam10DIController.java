package com.mycompany.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.myapp.service.Exam10Service1;
import com.mycompany.myapp.service.Exam10Service2;
import com.mycompany.myapp.service.Exam10Service3;
import com.mycompany.myapp.service.Exam10Service4;
import com.mycompany.myapp.service.Exam10Service5;
import com.mycompany.myapp.service.Exam10Service6;


@Controller
public class Exam10DIController {
    @Autowired
    private Exam10Service1 exam10Service1;
    @Autowired
    private Exam10Service2 exam10Service2;
    @Autowired
    private Exam10Service3 exam10Service3;
    @Autowired
    private Exam10Service4 exam10Service4;
    @Autowired
    private Exam10Service5 exam10Service5;
    @Autowired
    private Exam10Service6 exam10Service6;

	@RequestMapping("di/exam01")
    public String exam01() {        
        exam10Service1.join();
        exam10Service2.join();
        exam10Service3.join();
        exam10Service4.join();
        exam10Service5.join();
        exam10Service6.join();
        System.out.println();
        return "di/exam01";
    }
    
    @RequestMapping("di/exam02")
    public String exam02() {
        exam10Service1.login();
        exam10Service2.login();
        exam10Service3.login();
        exam10Service4.login();
        exam10Service5.login();
        exam10Service6.login();
        System.out.println();
        return "di/exam02";
    }
}
