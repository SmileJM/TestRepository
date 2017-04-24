package ch14.exam02;

public class LambdaExpressionExample {

	public static void main(String[] args) {
		method1(new FunctionalInterface1() {			
			@Override
			public void method() {
				System.out.println("run...");
			}
		});
		
		method1( () -> {
			System.out.println("run...");
		});
		
//		method2(new FunctionalInterface2() {			
//			@Override
//			public void method(int a) {
//				System.out.println(a + " run...");
//			}
//		});
		
		method2( (a) -> {
			System.out.println(a + " run...");
		});
		
		method2( a -> {
			System.out.println(a + " run...");
		});
		
		method2( a -> 	System.out.println(a + " run...")	);
		
		method3( (a, b) -> {
			System.out.println((a+b) + " run...");
		});
		
		method3( (a, b) -> System.out.println((a+b)  + " run...")	);
		
		method4( (a, b) -> {
//			System.out.println((a+b)  + " run...");
			return a+b;		
		});
		
		method4( (a, b) ->  a+b);
	}
	
	public static void method1(FunctionalInterface1 i) {
		i.method();
	}
	public static void method2(FunctionalInterface2 i) {
		i.method(3);
	}
	
	public static void method3(FunctionalInterface3 i) {
		i.method(3, 5);
	}
	
	public static void method4(FunctionalInterface4 i) {
		int result = i.method(3, 5);
		System.out.println("result: " + result);
	}

}
