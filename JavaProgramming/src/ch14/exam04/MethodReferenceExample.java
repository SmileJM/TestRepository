package ch14.exam04;

public class MethodReferenceExample {

	public static void main(String[] args) {
		
		method1(new FunctionalInterface1() {			
			@Override
			public int method(int a, int b) {				
				return Math.max(a, b);
			}			
		});
		
		method1(new FunctionalInterface1() {			
			@Override
			public int method(int a, int b) {
				return Calculator.staticSum(a, b);
			}			
		});
		
		method1(new FunctionalInterface1() {			
			@Override
			public int method(int a, int b) {
				return  Calculator.staticMulti(a, b);
			}			
		});
		
		method1( (a, b) -> { return Math.max(a, b);	});
		method1( (a, b) -> Math.max(a, b));
		method1( Math::max );
		
		method1( Math::min );
		
		method1( (a, b) -> { return Calculator.staticSum(a, b); });
		method1( (a, b) -> Calculator.staticSum(a, b));
		method1( Calculator::staticSum );
		
		method1( (a, b) -> {return Calculator.staticMulti(a, b);});
		method1( (a, b) -> Calculator.staticMulti(a, b) );
		method1( Calculator::staticMulti);
				
		Calculator cal = new Calculator();
		method1( (a, b) -> { return cal.minus(a, b);});
		method1( (a, b) -> cal.minus(a, b));
		method1( cal::minus);
		
		method1( cal::instanceSum );
		
		
	}	
	public static void method1(FunctionalInterface1 i) {
		int result = i.method(3, 5);
		System.out.println(result);
	}

}
