package ch06.exam04;

public class CarExample {

	public static void main(String[] args) {
		// 객체 생성
		Car myCar = new Car("2017-03-28", "검정");
		
		// Engine의 메소드 호출
		myCar.engine.start();
		myCar.engine.stop();
		
		// Dashboard의 메소드 호출
		myCar.dashboard.display(30);
		
		// Tire의 Field값 읽기
		String location = myCar.tires[0].location;
		System.out.println(location);
		
		// Tire의 메소드 호출
		myCar.tires[2].roll();
		
		// Car의 메소드 호출
		myCar.start();
		myCar.run();
		//----------------------------------------------------------
		// Tire 부품교체
		myCar.tires[1] = new Tire("새 앞좌");
		myCar.run();
		myCar.stop();

	}

}
