package com.mycompany.myapp.dao.sub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam10Dao3;

@Component("subExam10Dao3ImplA")
public class Exam10Dao3ImplA implements Exam10Dao3{
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam10Dao3ImplA.class);
	
    public void insert() {
    	LOGGER.info("회원 가입");        
    }
    public void select() {
    	LOGGER.info("회원 검색");
    }
}
