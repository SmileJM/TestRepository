package com.mycompany.myapp.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.myapp.ApplicationContextLoader;

public class ServiceTest extends ApplicationContextLoader {
	@Autowired
	private Exam15Service service;

	@Test
	public void writeTest() {
		// 테스트 코드
	}

	@Test
	public void getTest() {
		// 테스트 코드
	}
}
