package ch06.homework01.car03;

public class CarExample {

	public static void main(String[] args) {
		Car car = new Car();
		
		car.setGas(5);
		
		boolean gasState = car.isLeftGas();
		if(gasState) {
			System.out.println("출발합니다.");
			car.run();
		}
		
		if(car.isLeftGas()) {
			System.out.println("가스를 충전할 필요가 없습니다.");
		} else {
			System.out.println("가스를 주입하세요");
		}

	}

}
