package ch07.exam01;

public class Child extends Parent{
	// Field
	String firstName = "자바"; 
	// Constructor
	Child() {
		super();
		System.out.println("Child 객체 생성");
	}
	//Method
	void play() {
		System.out.println("놀아요");
	}
}
