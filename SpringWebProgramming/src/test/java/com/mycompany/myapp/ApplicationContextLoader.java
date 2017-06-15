package com.mycompany.myapp;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

// 기본 러너를 스프링이 지원하는 SpringJUnit4ClassRunner 로 바꾸겠다는 것
@RunWith(SpringJUnit4ClassRunner.class)

// 스프링 설정 파일이 무엇인지
@ContextConfiguration({ 
	// file - 파일 경로를 지정할 때 사용 / 프로젝트의 루트가 기준이됨
	// classpath - 클래스 패스에서 찾을 때 사용
	"file:WebContent/WEB-INF/spring/applicationContext.xml",
	"file:WebContent/WEB-INF/spring/dispatcher-servlet.xml" 
})

// WebApplication 을 테스트 하는 것이기 때문에 추가
// @WebAppConfiguration() 를 사용하려면 웹 컨텐트가 src/main/webapp 에 존재해야 함
// spring sts 로 프로젝트를 만들면 경로가 src/main/webapp/WEB-INF 가 기본 경로가 됨
// @WebAppConfiguration("WebContent") 해주면 설정 파일을 WebContent 에서 찾음
@WebAppConfiguration("WebContent")
public class ApplicationContextLoader {

}
