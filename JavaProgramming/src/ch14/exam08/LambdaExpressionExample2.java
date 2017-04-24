package ch14.exam08;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

public class LambdaExpressionExample2 {
	private static List<Student> list = Arrays.asList(
		new Student("홍길동", 90, 96),	
		new Student("감자바", 95, 93)			
	);
	
	public static void main(String[] args) {
		double englishAvg = avg( s -> s.getEnglishScore());
		System.out.println("영어 평균 점수: " + englishAvg);
		
		double mathAvg = avg( s -> s.getMathScore());
		System.out.println("수학 평균 점수: " + mathAvg);
		
		double avg = avg( s -> {
			return (s.getEnglishScore() +  s.getMathScore())/2;
		});
		System.out.println("평균 점수: " + avg);
		
	}
	
	public static double avg(ToDoubleFunction<Student> function) {
		double result = 0;
		for(Student student : list) {
			result += function.applyAsDouble(student);
		}
		return result /list.size();
	}	
}
