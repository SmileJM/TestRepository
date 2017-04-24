package ch14.exam07;

import java.util.function.DoubleToIntFunction;
import java.util.function.Function;

public class FunctionExample {

	public static void main(String[] args) {
		method1( (x)-> { return (int)x; });
		method1( (x)-> { return (int)Math.round(x); });
		
		method2( (m) -> {	return m.getmId(); });
		method2( (m) -> m.getmName());
		
	}
	
	public static void method1(DoubleToIntFunction arg) {
		int result = arg.applyAsInt(3.54);
		System.out.println(result);
	}
	
	public static void method2(Function<Member, String> arg) {
		Member member = new Member("white", "홍길동");
		String result = arg.apply(member);
		System.out.println(result);
	}
}
