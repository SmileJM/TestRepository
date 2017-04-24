package ch14.exam03;

public class LambdaExpressionExample {
	public static int a = 5;
	
	public static void main(String[] args) {
		method1( () -> {
			a = 7;
			System.out.println(a);
		});		
		int b = 5;		
		// 메소드 안은 로컬 크래스와 같음
		method1( () -> {
//			b = 7; // final 특성으로 변경 불가
			System.out.println(b);
			// 로컬 변수를 람다식에서 쓰는 것은 익명객체 안에서 쓰는 것과 같음
		});
	}
	
	public static void method1(FunctionalInterface1 i) {
		i.method();
	}
}
