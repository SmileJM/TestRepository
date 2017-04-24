package ch10.exam02;

import java.util.Scanner;

public class RuntimeExceptionExample {

	public static void main(String[] args) {
		try {
			String str = null;
			System.out.println(str.length());
		} catch(NullPointerException e) {
			System.out.println(e.getMessage());	
		} finally {
			System.out.println("11");
		}
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.print("입력: " );
			
			String strNum = scanner.nextLine();
			
			try {
				int num = Integer.parseInt(strNum);
			} catch(NumberFormatException e) {
				System.out.println("숫자만 입력하세요!");
			}

		
		}
	}

}
