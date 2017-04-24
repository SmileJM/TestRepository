package ch11.exam12;

import java.util.regex.Pattern;

public class PatternExample {

	public static void main(String[] args) {
		String regExp = "(02|010)-\\d{3,4}-\\d{4}";
		String data = "010-123-1234";
		boolean result = Pattern.matches(regExp, data);
		
		if(result) {
			System.out.println("정규 표현식과 일치함");
		} else {
			System.out.println("정규 표현식과 일치 안함");
		}
		
		regExp = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		data = "angel@navercom";
		
		result = Pattern.matches(regExp, data);
		
		if(result) {
			System.out.println("정규 표현식과 일치함");
		} else {
			System.out.println("정규 표현식과 일치 안함");
		}
		
	}

}
