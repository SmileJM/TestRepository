package ch11.exam15;

import java.util.Random;

public class RandomExample {

	public static void main(String[] args) {
//		for(int i=0; i<20; i++) {
		int rand = (int)(Math.random() * 6) +1;
		System.out.println(rand);
//		}
		
		int maxNum = 6;
		double random = Math.random();
		
		int rand2 = (int)(random * maxNum) +1;
		System.out.println(rand2);
		
		Random obj = new Random();
		int num2 = obj.nextInt(maxNum) +1;
		System.out.println(num2);
		
	}

}
