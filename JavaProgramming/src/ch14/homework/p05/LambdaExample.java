package ch14.homework.p05;

import java.util.function.IntBinaryOperator;

public class LambdaExample {
	private static int[] scores = { 10, 50, 3};
	
	public static void main(String[] args) {
//		int max = maxOrMin(	(x, y) -> Math.max(x, y));
		int max = maxOrMin(	(x, y) -> {
			if( x >=y) return x;
			else return y;
		});
		System.out.println("최댓값: " + max);
//		int min = maxOrMin(	(x, y) -> Math.min(x, y));
		int min = maxOrMin(	(x, y) -> {
			if( x <=y) return x;
			else return y;
		});
		System.out.println("최솟값: " + min);
	}
	
	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for( int score : scores) {
			result = operator.applyAsInt(result, score);
		}
		return result;
	}


}
