package ch06.exam03;

public class CalculatorExample {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		
		calc.powerOn();
		String info = calc.info();
		System.out.println(info);
		
		int result1 = calc.plus(10, 20);
		System.out.println(result1);
		
		double result2 = calc.divide(10.5, 5.2);
		System.out.println(result2);
		
		int[] result3 = calc.changeArray(10, 20, 30);
		for(int value : result3) {
			System.out.print(value + " ");
		}
		System.out.println();
		
		Car result4 = calc.makeCar("검정", true);
		System.out.println(result4.color);
		System.out.println(result4.airback);
		
	}

}
