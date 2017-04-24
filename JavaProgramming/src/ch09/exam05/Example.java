package ch09.exam05;

public class Example {
	public static void main(String[] args) {
		A a = new A();
		A.B b =  a.new B();		

		A.C c = new A.C();
	}
}
