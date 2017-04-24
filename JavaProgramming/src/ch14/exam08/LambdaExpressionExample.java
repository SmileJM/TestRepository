package ch14.exam08;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class LambdaExpressionExample {
	private static List<Student> list = Arrays.asList(
		new Student("홍길동", 90, 96),	
		new Student("감자바", 95, 93)			
	);
	public static void main(String[] args) {
		printString( s -> s.getName());		
//		printInt( s -> {return s.getEnglishScore();});		
//		printInt( s -> s.getEnglishScore());		
//		printInt( s -> s.getMathScore());
	}
	
	public static void printString(Function<Student, String> function) {
		for(Student student : list) {
			System.out.print(function.apply(student) + " ");
		}
		System.out.println();
	}
	
//	public static void printInt(Function<Student, Integer> function) {
	public static void printInt(ToIntFunction<Student> function) {
		for(Student student : list) {
			System.out.print(function.applyAsInt(student) + " ");
//			System.out.print(function.apply(student) + " ");
		}
		System.out.println();
	}
	


}
