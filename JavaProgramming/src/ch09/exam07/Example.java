package ch09.exam07;

public class Example{	
	public static void main(String[] args) {
		// 로컬 클래스를 정의하고 객체 생성
		class B2 extends A.B {
			@Override
			void bMethod() {
				System.out.println("B2-bMethod()");
			}
		}
		B2 b2 = new B2();
		b2.bMethod();
		
		A.B b = new B2();
		b.bMethod();
		
		// 위의 코드를 간단하게
		// B를 상속해서 B의 자식객체를 만드는 것
		// 중괄호 블럭은 위의 중괄호 블럭과 같음
		// 익명 구현 객체 
		// -> 위에는 B2라는 이름이 있지만 여기는 없음
		A.B b3 = new A.B() {
			@Override
			void bMethod() {
				System.out.println("B2-bMethod()");
			}
		};
		b3.bMethod();
	}
}
