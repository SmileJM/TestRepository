package ch09.exam04;

public class A {
	int field;
	// Nested Class
	class B {
		int field;
		void method() {
			field = 10;
			A.this.field = 10; // 안드로이드에서 가끔 나옴			
		}
	}
//	static class C {}
}
