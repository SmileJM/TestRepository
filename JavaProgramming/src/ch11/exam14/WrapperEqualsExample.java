package ch11.exam14;

public class WrapperEqualsExample {

	public static void main(String[] args) {
		Integer v1 = 128;
		Integer v2 = 128;
		
		System.out.println(v1 == v2);
		System.out.println(v1.intValue() == v2.intValue());
		System.out.println(v1.equals(v2));
	}
}
