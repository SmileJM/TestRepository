package com.mycompany.myapp.test;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

	@Override
    public String execute(Connection<?> connection) {
        Member member = new Member();
        member.setUsesrname(connection.getDisplayName());   
        
        return member.getUsesrname();
    }
}