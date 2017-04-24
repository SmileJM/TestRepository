package ch14.exam05;

public class MethodReferenceExample {

	public static void main(String[] args) {
		method1(new FunctionalInterface1() {			
			@Override
			public boolean method(String a, String b) {				
				return a.equals(b);
			}
		});
		
		method1( (a, b) -> { return a.equals(b); });
		method1( (a, b) -> a.equals(b));
		method1(String::equals);
				
	}	
	public static void method1(FunctionalInterface1 i) {
		boolean result = i.method("java", "Java");
		System.out.println(result);
	}

}
