package ch07.exam01;

public class ChildExample {

	public static void main(String[] args) {
		Child child = new Child();
		System.out.println(child.lastName);
		child.sound();
		
		System.out.println(child.firstName);
		child.play();
	}

}

