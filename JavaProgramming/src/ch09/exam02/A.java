package ch09.exam02;

public class A {
//	static int aField;
	int aField;
	
	A() {
		class D {
			void dMethod() {
				aField = 10;
			}
		}
	
	}
	
	void aMethod() {

		class E {
			void eMethod() {
				aField = 10;
			}
		}
		
	}
	// Nested Class
	class B {
		void bMethod() {
			aField = 10;
		}
	}
	static class C {
		void eMethod() {
//			aField = 10;
		}
	}
}
