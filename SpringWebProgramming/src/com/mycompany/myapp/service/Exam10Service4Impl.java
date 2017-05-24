package com.mycompany.myapp.service;

import com.mycompany.myapp.dao.Exam10Dao2;

public class Exam10Service4Impl implements Exam10Service4 {

	private Exam10Dao2 exam10Dao2;

	public void setExam10Dao(Exam10Dao2 exam10Dao2) {
		this.exam10Dao2 = exam10Dao2;
	}

	@Override
	public void join() {
		System.out.println("Exam10Service4Impl - join() 실행");
		exam10Dao2.insert();
	}

	@Override
	public void login() {
		System.out.println("Exam10Service4Impl - login() 실행");
		exam10Dao2.select();
	}
}
