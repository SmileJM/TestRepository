package ch09.exam07;

public class Example2{	
	public static void main(String[] args) {
		// 로컬 클래스를 정의하고 객체 생성
		class CImple implements A.C {
			@Override
			public void cMethod() {
				System.out.println("CImple-cMethod()");	
			}
		}
		CImple cimple = new CImple();
		cimple.cMethod();
		A.C c=  new CImple();
		c.cMethod();

		// 원래 인터페이스는 생성자가 없지만..
		// 뒤어 { } 블록이 나오면 가능함
		// CImple을 위해서 클래스를 만들기가 그렇기 때문에
		// new CImple() 객체를 사용해서 넣어주는 것
		A.C c3 = new A.C(){
			@Override
			public void cMethod() {
				System.out.println("CImple-cMethod()");	
			}
		};
		c3.cMethod();
	}
}
