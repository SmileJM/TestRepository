package com.mycompany.myapp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam10Dao3;

@Component
public class Exam10Service6Impl implements Exam10Service6 {
	// @Autowired Exam10Dao3 타입만을 보고 대입함
	// 구현 객체가 2개 이상일 경우 어떤 것을 넣을 지 모름
	// 스프링은 객체를 키(id, 이름)로 관리하기 때문에
	// 이름으로 객체를 찾을 수 있도록 수정해야 함
	// @Autowired 대안이 @Resource(name="exam10Dao3ImplA")	
	// Resource 의 역할이 이름으로 찾아서 등록하겠다는 것
	@Resource(name="exam10Dao3ImplA")
	private Exam10Dao3 exam10Dao3;
	
	@Override
	public void join() {
		System.out.println("Exam10Service5Impl - join() 실행");
		exam10Dao3.insert();
	}

	@Override
	public void login() {
		System.out.println("Exam10Service5Impl - login() 실행");
		exam10Dao3.select();
	}
}
