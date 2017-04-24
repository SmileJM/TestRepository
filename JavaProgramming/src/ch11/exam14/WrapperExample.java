package ch11.exam14;

public class WrapperExample {

	public static void main(String[] args) {
		int v1 = 10;
		Integer v2 = 10;
		int v3 = v2;
		// v2가 가지고 있는 내부의 값을 꺼내어 v3에 저장
		
		method1(3);
		int v4 = method2();
		
	}
	
	public static void method1(Integer obj) {
		
	}
	public static Integer method2() {
		return 1;
	}

}
