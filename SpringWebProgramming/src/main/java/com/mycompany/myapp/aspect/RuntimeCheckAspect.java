package com.mycompany.myapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect // 일반요소가 아니고 Aspect 이기 때문에 붙여줌
public class RuntimeCheckAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeCheckAspect.class);
	// PointCut
	@Pointcut("execution(public * com.mycompany.myapp.controller.Exam12DBController.*(..))")
	// 어떤 메소드를 실행할 때 공통 관심사를 넣을 것인지를 () 안에 넣어줌
	// public 인 메소드 중에서 / 리턴타입은 상관 없고 / 위의 클래스에서 / 모든 메소드
	// 모든 메소드라고 하기 위해서 .*() 를 사용하고 / 매개변수의 수는 상관없다는 의미로 (..) 을 붙임

	// 아래의 메소드는 아무 의미 없음 / 포인트 컷을 찾아가기 위해서 하나가 있어야 함 / @Around 에 넣어주기 위해서 필요한 것
	// 이름 조차 의미 없음 / 포인트 컷을 설정해 놓는 것임
	private void runtimeCheckMethod() {
	}

	// Advice
	@Around("runtimeCheckMethod()")
	// Around 어드파이스를 적용할 메소드를 지정 하는 것 /
	public Object runtimeCheckMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		// before code
		long startTime = System.nanoTime();
		
		// 어떤 타입을 리턴할 지 모르기 때문에 Object 타입
		// result : 실행 결과의 리턴 값
		Object result = joinPoint.proceed(); // 실제 메소드 호출
		
		// after code
		long endTime = System.nanoTime();
		long time = endTime -startTime;
		String realMethod = joinPoint.getSignature().toShortString(); // toShortString() 메소드 이름만 // toString() 클래스 이름까지 다 나옴
		LOGGER.info(realMethod + " 실행 시간: " + time + " ns");
		
		return result;
	}
}
