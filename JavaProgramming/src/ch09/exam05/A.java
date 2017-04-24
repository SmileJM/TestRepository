package ch09.exam05;

public class A {
	
	A() {
		class D {}
		D d = new D();
	}
	
	void method() {
		class E {}
		E e = new E();
	}
	// Nested Class
	class B {}
	static class C {}
}
