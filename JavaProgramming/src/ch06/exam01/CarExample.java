package ch06.exam01;

public class CarExample {

	public static void main(String[] args) {
		Car myCar = new Car();
		myCar.run();
		
		System.out.println(myCar.company);
		System.out.println(myCar.speed);
		System.out.println(myCar.color);
		System.out.println(myCar.airback);
		
		myCar.speed = 30;
		myCar.color = "흰색";
		System.out.println(myCar.color);
		myCar.run();
		
		/////////
		
		Car yourCar = new Car();
	}

}
