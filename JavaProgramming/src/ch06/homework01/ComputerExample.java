package ch06.homework01;

public class ComputerExample {

	public static void main(String[] args) {
		Computer com = new Computer();
		int[] value1 = { 1, 2, 3 };
		
		int result1 = com.sum1(value1);
		System.out.println("result1: " + result1);
		
		int result2 = com.sum1(new int[] {2, 3, 4});
		System.out.println("result2: " + result2);
		
		int result3 = com.sum2(1,2,3,4);
		System.out.println("result3: " + result3);
		
		int result4 = com.sum2(1,2,3,4,5,6,7);
		System.out.println("result4: " + result4);
		
	}

}
