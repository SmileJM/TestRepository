package ch14.test01;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class MemberEx {
	static Member[] members = {
			new Member("가", 20, "0000000000"),
			new Member("나", 30, "1111111111"),
			new Member("다", 40, "2222222222")
	};
	
	static Member member = new Member("가", 20, "0000000000");
	public static void main(String[] args) {
		method1( (a) -> { 
			int value = a.getAge();
			return value;
		});
		
		method2( a -> a.getName() );
		method2( a -> a.getPhone() );
	}
	
	public static void method1(ToIntFunction<Member> function) {	
		for(Member member : members) {
			int result = function.applyAsInt(member);
			System.out.println(result);
		}		
	}
	public static void method2(Function<Member, String> function) {	
		for(Member member : members) {
			String result = function.apply(member);
			System.out.println(result);
		}		
	}
}
