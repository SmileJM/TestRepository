package ch14.exam07;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

public class SupplierExample {

	public static void main(String[] args) {
		method1(new Consumer<String> (){
			@Override
			public void accept(String t) {	System.out.println(t); }
		});
		
		method1( t-> System.out.println(t) );		
		method2( (a, b) ->	System.out.println(a + " - " + b));
		method3( (a, b) ->	{
			for( int i=0; i<b; i++) {
				System.out.println(a);		
			}
		});
	}
	
	public static void method1(Consumer<String> arg) {
		arg.accept("Java");
	}
	
	public static void method2(BiConsumer<String, String> arg) {
		arg.accept("Iot", "Java");
	}
	
	public static void method3(ObjIntConsumer<String> arg) {
		arg.accept("홍길동", 2);
	}

}
