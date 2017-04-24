package ch06.exam02;

public class CarExample {

	public static void main(String[] args) {
		Car myCar = new Car();
		
		System.out.println(myCar.company);
		System.out.println(myCar.speed);
		System.out.println(myCar.color);
		System.out.println(myCar.airback);
		System.out.println();

		Car yourCar = new Car("검정", true);
		
		System.out.println(yourCar.company);
		System.out.println(yourCar.speed);
		System.out.println(yourCar.color);
		System.out.println(yourCar.airback);
		System.out.println();
		
		yourCar = new Car("빨강", false);
		
		System.out.println(yourCar.company);
		System.out.println(yourCar.speed);
		System.out.println(yourCar.color);
		System.out.println(yourCar.airback);
	}

}
