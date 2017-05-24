package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam10Dao1;

@Component
public class Exam10Service2Impl implements Exam10Service2 {

	private Exam10Dao1 exam10Dao;
	// Setter 주입
	@Autowired
	public void setExam10Dao2(Exam10Dao1 exam10Dao) {
		this.exam10Dao = exam10Dao;
	}

	@Override
	public void join() {
		System.out.println("Exam10Service2Impl - join() 실행");
		exam10Dao.insert();
	}

	@Override
	public void login() {
		System.out.println("Exam10Service2Impl - login() 실행");
		exam10Dao.select();
	}
}
