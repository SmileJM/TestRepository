package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam10Dao1;

@Component
public class Exam10Service3Impl implements Exam10Service3 {

	private Exam10Dao1 exam10Dao;

	// 생성자 주입
	@Autowired
	public Exam10Service3Impl(Exam10Dao1 exam10Dao) {
		this.exam10Dao = exam10Dao;
		// 추가적인 초기화 코드
	}

	@Override
	public void join() {
		System.out.println("Exam10Service3Impl - join() 실행");
		exam10Dao.insert();
	}

	@Override
	public void login() {
		System.out.println("Exam10Service3Impl - login() 실행");
		exam10Dao.select();
	}
}
