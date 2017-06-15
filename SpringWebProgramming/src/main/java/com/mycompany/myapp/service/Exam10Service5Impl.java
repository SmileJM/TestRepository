package com.mycompany.myapp.service;

import com.mycompany.myapp.dao.Exam10Dao2;

public class Exam10Service5Impl implements Exam10Service5 {

	private Exam10Dao2 exam10Dao2;

	public Exam10Service5Impl(Exam10Dao2 exam10Dao2) {
		this.exam10Dao2 = exam10Dao2;
	}

	@Override
	public void join() {
		System.out.println("Exam10Service5Impl - join() 실행");
		exam10Dao2.insert();
	}

	@Override
	public void login() {
		System.out.println("Exam10Service5Impl - login() 실행");
		exam10Dao2.select();
	}
}
