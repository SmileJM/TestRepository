package ch09.homework.p02;

public class A {
	B field1 = new B();
	C fieldl2 = new C();
	
	void method1() {
		B var1 = new B();
		C var2 = new C();
	}
//	static B field3 = new B();
	static C field4 = new C();
	
	class B {}
	static class C {}
}
